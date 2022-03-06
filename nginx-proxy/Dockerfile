FROM centos
MAINTAINER linguohao111@gmail.com
RUN cd /etc/yum.repos.d/
RUN sed -i 's/mirrorlist/#mirrorlist/g' /etc/yum.repos.d/CentOS-*
RUN sed -i 's|#baseurl=http://mirror.centos.org|baseurl=http://vault.centos.org|g' /etc/yum.repos.d/CentOS-*
RUN yum -y  install wget gcc gcc-c++ glibc make autoconf openssl openssl-devel libxml2 libxslt-devel gd-devel pcre-devel 
ADD http://nginx.org/download/nginx-1.19.9.tar.gz /opt/nginx/
RUN tar -xvzf /opt/nginx/nginx-1.19.9.tar.gz -C /usr/local/src/ \
&& useradd -M -s /sbin/nologin nginx
WORKDIR /usr/local/src/nginx-1.19.9
RUN ./configure --user=nginx --group=nginx --prefix=/usr/local/nginx --with-http_ssl_module --with-http_v2_module --with-file-aio --with-http_ssl_module --with-http_realip_module --with-http_addition_module --with-http_xslt_module --with-http_image_filter_module  --with-http_sub_module --with-http_dav_module --with-http_flv_module --with-http_mp4_module --with-http_gunzip_module --with-http_gzip_static_module --with-http_auth_request_module --with-http_random_index_module --with-http_secure_link_module --with-http_degradation_module --with-http_stub_status_module && make && make install
RUN rm -rf /opt/nginx/nginx-1.19.9.tar.gz
ENV PATH=/usr/local/nginx/sbin:$PATH
EXPOSE 80
CMD /bin/sh -c 'nginx -g "daemon off;"'
