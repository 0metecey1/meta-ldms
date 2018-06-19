# Appends the virtual/kernel recipe from Toradex for Colibri VF61
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-toradex-4.4:"

SRC_URI_append_colibri-vf-ldms = " file://0001-Changed-pin-muxing-enabled-dspi2-dspi3-i2c3-added-ad.patch"
SRC_URI_append_colibri-vf-ldms = " file://0002-configuration-for-output-direction-inverted-non-inve.patch"
SRC_URI_append_colibri-vf-ldms = " file://0003-enabled-JC42-temperature-sensor-AT24-EEPROM-PCA9632-.patch"
SRC_URI_append_colibri-vf-ldms = " file://0004-disabled-all-i2c3-device-tree-nodes-to-allow-hot-plu.patch"
SRC_URI_append_colibri-vf-ldms = " file://0005-added-spidev-device-driver-for-dspi1-to-enable-the-A.patch"
SRC_URI_append_colibri-vf-ldms = " file://defconfig"
