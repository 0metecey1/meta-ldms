SUMMARY_append_colibri-vf-ldms = " (LDMS)"

IMAGE_NAME_colibri-vf-ldms = "Colibri-VF-LDMS_${IMAGE_BASENAME}"
BURNFLASH := "${THISDIR}/files/${MACHINE}"

IMAGE_INSTALL_append = " zeromq"
IMAGE_INSTALL_append = " mariadb"
IMAGE_INSTALL_append = " lua"
IMAGE_INSTALL_append = " boost"

COPY_LIC_DIRS_colibri-vf-ldms = "0"
