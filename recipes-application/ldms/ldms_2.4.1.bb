#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

DESCRIPTION = "Main control application for Novaled VOST or ldms"
SECTION = "application"

DEPENDS = "czmq zeromq lua-native lua jansson mariadb i2c-tools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=96af5705d6f64a88e035781ef00e98a8"

# This tells bitbake where to find the files we're providing on the local filesystem
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

# Use local tarball
SRC_URI = "file://${PN}-${PV}.tar.gz"
SRC_URI_append = " file://ldms.service"

inherit autotools pkgconfig systemd

do_install_append() {
    # Copy service file
    install -d ${D}/${systemd_unitdir}/system
    install -c -m 644 ${WORKDIR}/ldms.service ${D}/${systemd_unitdir}/system
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_SERVICE_${PN} = "ldms.service"
FILES_${PN} = "${bindir}/${PN} ${datadir} ${datadir}/${PN} ${libdir}/lua/5.3/*.so"
