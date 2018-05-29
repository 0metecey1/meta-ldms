#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

DESCRIPTION = "Beacon for network discovery of the VOST ports"
SECTION = "nlts"
DEPENDS = "czmq zeromq"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c8153c155d760c5cd43cdb0b9bedffff"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRCREV = "4c5e45290f9197ed4aa3882e31d5c49bdcf11449"
SRC_URI = "git://git@gitserver/opt/git/nltsbeac.git;protocol=ssh"
SRC_URI += "file://nltsbeac.service"

S = "${WORKDIR}/git"

inherit autotools systemd

SYSTEMD_SERVICE_${PN} = "nltsbeac.service"

do_install_append () {
	# install systemd unit files
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/nltsbeac.service ${D}${systemd_unitdir}/system
}
# The autotools configuration I am basing this on seems to have a problem with a race condition when parallel make is enabled
PARALLEL_MAKE = ""
