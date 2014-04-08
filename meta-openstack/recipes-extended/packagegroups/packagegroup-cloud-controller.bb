SUMMARY = "Configuration for OpenStack Controller node"
PR = "r0"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit packagegroup

RDEPENDS_${PN} = " postgresql-setup \
    postgresql \
    postgresql-client \
    python-psycopg2 \
    rabbitmq-server \
    nova-setup \
    nova-controller \
    keystone-setup \
    glance \
    glance-api \
    glance-registry \
    glance-setup \
    keystone \
    keystone-cronjobs \
    neutron-setup \
    neutron-server \
    neutron-plugin-openvswitch \
    openvswitch-controller \
    cinder-api \
    cinder-volume \
    cinder-scheduler \
    cinder-backup \
    cinder-setup \
    ceilometer-setup \
    ceilometer-api \
    ceilometer-collector \
    ceilometer-controller \
    heat-api \
    heat-api-cfn \
    heat-engine \
    heat-setup \
    python-heat-cfntools \
    python-openstackclient \
    horizon \
    horizon-standalone \
    horizon-apache \
    apache2 \
    novnc \
    chkconfig \
    qemu \
    fuse \
    ${@base_contains('CINDER_EXTRA_FEATURES', 'glusterfs', 'glusterfs glusterfs-fuse glusterfs-server', '', d)} \
    ${@base_contains('CINDER_EXTRA_FEATURES', 'ceph', 'task-ceph ceph-setup xfsprogs', '', d)} \
    "

RRECOMMENDS_${PN} = " \
    kernel-module-fuse \
    cloud-init \
    "
