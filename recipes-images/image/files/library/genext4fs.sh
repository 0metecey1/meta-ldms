#!/bin/sh
#creates a file containing a ext4 binary blob of the content of the ../rootfs folder

# sometimes we need the binary echo, not the shell builtin
ECHO=`which echo`

MOUNTPOINT="mnt/tdx-rootfs"

Usage()
{
	echo "creates a file containing a ext4 binary blob. Output file name taken from command line"
	echo "Usage: genext4fs.sh [OPTION]... dest_file_name"
	echo "-h : Prints this message"
	echo "-b : Sets the partition size in MBytes, default 256"
	echo "-d : Selects the directory which contains the partition content, default ../rootfs"
	echo ""
}

PARTITIONSIZE=256
SRCPATH=../rootfs

while getopts "b:d:h" Option ; do
	case $Option in
		h) Usage
			# Exit if only usage (-h) was specfied.
			if [[ "$#" -eq "1" ]] ; then
				exit 10
			fi
			exit 0
			;;
		b) PARTITIONSIZE=$OPTARG
			;;
		d) SRCPATH=$OPTARG
			;;
	esac
done
#adjust the commandline arg for the now used options
shift $(($OPTIND - 1))

#create the file used for the loopbackdevice and format it for ext4
#answer y to "is not a block special device"
dd if=/dev/zero of=$@ bs=1024k count=$PARTITIONSIZE
mkfs.ext4 -F -L rootfs $@
#disable fsck checks based on mount count or time interval
tune2fs -c 0 -i 0 $@

#prepare mountpoint for the loopdevice
sudo mkdir -p $MOUNTPOINT
sudo umount $MOUNTPOINT &> /dev/null
sudo rm -rf $MOUNTPOINT/*
#mount the file as a loopdevice
#there seems to be a race between mkfs.ext4 not yet finished and mount already trying to mount, so try until it works
MOUNTED=0
while [ "$MOUNTED" -eq  "0" ] ; do
	sync
	sleep 1
	sudo mount $@ $MOUNTPOINT/ -o loop -t ext4
	MOUNTED=`mount | grep -c "$MOUNTPOINT"`
done
#extract rootfs into the file
sudo cp -a $SRCPATH/* $MOUNTPOINT
if [ "$?" -ne "0" ] ; then
	$ECHO -e "\033[1mCopying the rootfs failed.\033[0m"
	echo "Check for error messages from cp"
	sudo rm $@
	exit 1
fi

#unmount again
sudo umount $MOUNTPOINT

#if the mounting was unsuccesful all rootfs data now still is in MOUNTPOINT, and $@ contains an empty fs
#delete $@ in that case to fail early
sync
FAILED=`ls $MOUNTPOINT | wc -l`
if [ "$FAILED" -ne "0" ] ; then
	$ECHO -e "\033[1mMounting the loopdevice $@ failed.\033[0m"
	sudo rm $@
	exit 1
fi
