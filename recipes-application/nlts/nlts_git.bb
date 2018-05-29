#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

DESCRIPTION = "Simple Boost helloworld application"
SECTION = "examples"
DEPENDS = "boost zeromq czmq mariadb"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c8153c155d760c5cd43cdb0b9bedffff"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
FILES_${PN} += "${prefix}/local/share/lua ${prefix}/local/lib/lua"  

# SRCREV = "a5ae05e51dbead4ea660fea760631c0cb5d6c59f"
SRCREV = "${AUTOREV}"
SRC_URI = "git://git@gitserver//opt/git/nlts.git;protocol=ssh"
SRC_URI += "file://nlts.service"

S = "${WORKDIR}/git"

inherit autotools systemd

SYSTEMD_SERVICE_${PN} = "nlts.service"

do_install_append () {
	# install systemd unit files
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/nlts.service ${D}${systemd_unitdir}/system
}
# The autotools configuration I am basing this on seems to have a problem with a race condition when parallel make is enabled
PARALLEL_MAKE = ""
