SUMMARY = "Tools for ISO9960 filesystems"
HOMEPAGE = "http://cdrkit.org"
SECTION = "console/utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b30d3b2750b668133fc17b401e1b98f8"

SRC_URI = "${DEBIAN_MIRROR}/main/c/${BPN}/${BPN}_${PV}.orig.tar.gz;name=tarball"

SRC_URI[md5sum] = "efe08e2f3ca478486037b053acd512e9"
SRC_URI[sha256sum] = "d1c030756ecc182defee9fe885638c1785d35a2c2a297b4604c0e0dcc78e47da"

BBCLASSEXTEND = "native"

EXTRA_OEMAKE = "PREFIX=${prefix}"

DEPENDS = "libcap zlib file bzip2 perl"

RDEPENDS_dirsplit = "perl"

PACKAGES = "cdrkit-dbg wodim genisoimage dirsplit icedax libusal libusal-dev"

FILES_wodim = " \
	${bindir}/devdump \
	${bindir}/wodim \
	${bindir}/readom \
	${sbindir}/netscsid \
	${mandir}/man1/devdump.* \
	${mandir}/man1/wodim.* \
	${mandir}/man1/netscsid.* \
	${mandir}/man1/readom.* \
	"

FILES_icedax = " \
	${bindir}/icedax \
	${bindir}/cdda2mp3 \
	${bindir}/cdda2ogg \
	${mandir}/man1/icedax.* \
	${mandir}/man1/cdda2ogg.* \
	${mandir}/man1/list_audio_tracks.* \
	"

FILES_genisoimage = " \
	${bindir}/genisoimage \
	${bindir}/isodebug \
	${bindir}/isodump \
	${bindir}/isoinfo \
	${bindir}/isovfy \
	${bindir}/pitchplay \
	${bindir}/readmult \
	${mandir}/man5/genisoimagerc.* \
	${mandir}/man1/genisoimage.* \
	${mandir}/man1/isodebug.* \
	${mandir}/man1/isodump.* \
	${mandir}/man1/isoinfo.* \
	${mandir}/man1/isovfy.* \
	${mandir}/man1/pitchplay.* \
	${mandir}/man1/readmult.* \
	"

FILES_dirsplit = " \
	${bindir}/dirsplit \
	${mandir}/man1/dirsplit.* \
	"

FILES_libusal = " \
	${libdir}/libusal.so.* \
	${libdir}/librols.so.* \
	"

FILES_libusal-dev = " \
	${libdir}/libusal.so \
	${libdir}/librols.so \
	${includedir}/usal \
	"

do_compile() {
        oe_runmake
}

do_install() {
        oe_runmake 'DESTDIR=${D}' install
}
