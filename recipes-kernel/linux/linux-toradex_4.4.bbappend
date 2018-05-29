#include conf/tdx_version.conf

FILESEXTRAPATHS_prepend := "${THISDIR}/linux-toradex-4.4:"

# LOCALVERSION = "-${TDX_VER_ITEM}"

# SRCREV = "a126a5e5dc2fcc5cb36af14c89b440cc8e3bab30"
# SRCBRANCH = "toradex_vf_4.4"
# SRCREV_use-head-next = "${AUTOREV}"
# SRCBRANCH_use-head-next = "toradex_vf_4.4-next"
#PR = "2.8b1-ldms"

# SRC_URI += "file://0001-calibrate.c-Added-some-printk-statements.patch"
# SRC_URI += "file://0001-add-ldms-specific-devicetree.patch"
# SRC_URI += "file://0001-include-ldms-devicetree-in-Makefile.patch"
# SRC_URI += "file://defconfig"
SRC_URI_append_colibri-vf-ldms = " file://0001-calibrate.c-Added-some-printk-statements.patch"
SRC_URI_append_colibri-vf-ldms = " file://0001-add-ldms-specific-devicetree.patch"
SRC_URI_append_colibri-vf-ldms = " file://0001-include-ldms-devicetree-in-Makefile.patch"
SRC_URI_append_colibri-vf-ldms = " file://defconfig"


# COMPATIBLE_MACHINE = "(colibri-vf-ldms)"
