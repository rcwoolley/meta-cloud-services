DESCRIPTION = "Linux SCSI target framework (tgt)"
HOMEPAGE = "http://stgt.sourceforge.net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://scripts/tgtd.spec;beginline=7;endline=7;md5=21c19ea7dad04648b9c2f791b6e29b4c"
DEPENDS = "sg3-utils"

SRCREV = "0ee382bab57a8ecd9ece18d511bd098298478409"
PV = "1.0.36+git${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/fujita/${PN}.git;protocol=git \
    file://tgtd.init"

S = "${WORKDIR}/git"

do_compile() {
    oe_runmake -e programs conf scripts
}

do_install() {
    #Quick don't build docs
    oe_runmake -e DESTDIR="${D}" install-programs install-conf install-scripts

    if ${@base_contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${WORKDIR}/tgtd.init ${D}${sysconfdir}/init.d/tgtd
    fi
}

RDEPENDS_${PN} = "perl-tests \
    libconfig-general-perl \
    perl-module-english \
    perl-module-tie-hash-namedcapture \
    perl-module-xsloader \
    perl-module-carp \
    perl-module-exporter \
    perl-module-errno \
    perl-module-exporter-heavy \
    perl-module-symbol \
    perl-module-selectsaver \
    perl-module-dynaloader \
    perl-module-carp-heavy \
    perl-module-filehandle \
    perl-module-feature \
    perl-module-overload \
    perl-module-fcntl \
    perl-module-io \
    perl-module-io-file \
    perl-module-io-handle \
    perl-module-io-seekable \
    perl-module-file-glob \
    perl-module-base \
    perl-module-encoding-warnings \
    perl-module-file-spec-unix \
    perl-module-file-spec \
    perl-module-file-spec-functions \
    "

INITSCRIPT_NAME_${tgt} = "tgtd"