#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

DESCRIPTION = "Main control application for Novaled VOST or ldms"
SECTION = "examples"
# RDEPENDS_${PN} = "ldms-gpio czmq zeromq lua jansson mariadb"
RDEPENDS_${PN} = "czmq zeromq lua jansson mariadb"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=96af5705d6f64a88e035781ef00e98a8"

PR = "r7"

# This tells bitbake where to find the files we're providing on the local filesystem
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

# Use local tarball
# SRC_URI = "git://git@gitserver/opt/git/ldms.git;protocol=ssh;branch=code_reorganization"
SRC_URI = "git://git@localhost/home/git/repos/ldms.git;protocol=ssh;branch=feat/lua53"

# SRCBRANCH = "feat/lua53"
# SRCREV = "34967ebade2fab61087463218cd86c6d9af141ed"
# SRCREV = "17a5cff827a974c484c49f0b9213269d413bb8cf"
SRCREV = "17a5cff827a974c484c49f0b9213269d413bb8cf"

# SRCREV = "${AUTOREV}"

# Make sure our source directory (for the build) matches the directory structure in the tarball
# S = "${WORKDIR}/ldms-${PV}"
S = "${WORKDIR}/git"

inherit autotools
inherit systemd

do_install_append() {
    # Copy service file
    install -d ${D}/${systemd_unitdir}/system
    install -c -m 644 ${S}/ldms.service ${D}/${systemd_unitdir}/system
}


# The autotools configuration I am basing this on seems to have a problem with a race condition when parallel make is enabled
PARALLEL_MAKE = ""

NATIVE_SYSTEMD_SUPPORT = "1"
# SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "ldms.service"
FILES_${PN} = "${bindir}/${PN} ${datadir} ${datadir}/${PN}"
FILES_${PN} += " ${datadir}/${PN}/init.lua"
FILES_${PN} += " ${datadir}/${PN}/exit.lua"
