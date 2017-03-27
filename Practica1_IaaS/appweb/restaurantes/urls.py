# -*- coding: utf-8 -*-
# restaurantes/urls.py

from django.conf.urls import url

from . import views

# 2 Funciones que corresponden a los nombres indicados en atributo name y cuya
# definición se encuentra en el archivo views.py
urlpatterns = [
    url(r'^$', views.index, name='index'),
    url(r'^test/$', views.test, name='test'),
    url(r'^buscar$', views.buscar, name='buscar'),
    url(r'^aniadir$', views.add, name='add'),
]
