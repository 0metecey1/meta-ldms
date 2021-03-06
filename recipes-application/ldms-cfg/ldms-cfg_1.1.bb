#
# Install a systemd service that sets the GPIOs used in the LDMS board
#

inherit systemd
DESCRIPTION = "ldms GPIO setup snd configuration ystemd service"
SECTION = "examples"
LICENSE = "CLOSED"

# This tells bitbake where to find the files we're providing on the local filesystem
# FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

# Use local systemd service file
SRC_URI = "file://ldms-cfg.service"
SRC_URI += "file://ldms-cfg"

# Make sure our source directory (for the build) matches the directory structure in the tarball
# S = "${WORKDIR}"


do_install() {
    # Copy service file
    install -d ${D}/${systemd_unitdir}/system
    install -c -m 644 ${WORKDIR}/ldms-cfg.service ${D}/${systemd_unitdir}/system
    # Copy shell script
    install -d ${D}/${bindir}
    install -c -m 755 ${WORKDIR}/ldms-cfg ${D}/${bindir}
}

NATIVE_SYSTEMD_SUPPORT = "1"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "ldms-cfg.service"
FILES_${PN} = "${bindir}/ldms-cfg"
