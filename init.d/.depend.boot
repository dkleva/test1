TARGETS = console-setup mountkernfs.sh resolvconf ufw apparmor plymouth-log screen-cleanup hostname.sh ebtables udev keyboard-setup cryptdisks cryptdisks-early open-iscsi networking iscsid urandom hwclock.sh checkroot.sh lvm2 checkfs.sh mountdevsubfs.sh mountall.sh mountall-bootclean.sh bootmisc.sh mountnfs-bootclean.sh mountnfs.sh kmod checkroot-bootclean.sh procps
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
open-iscsi: networking iscsid
networking: mountkernfs.sh urandom resolvconf procps
iscsid: networking
urandom: hwclock.sh
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks lvm2 checkroot.sh
mountdevsubfs.sh: mountkernfs.sh udev
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
mountall-bootclean.sh: mountall.sh
bootmisc.sh: mountall-bootclean.sh mountnfs-bootclean.sh checkroot-bootclean.sh udev
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
kmod: checkroot.sh
checkroot-bootclean.sh: checkroot.sh
procps: mountkernfs.sh udev
