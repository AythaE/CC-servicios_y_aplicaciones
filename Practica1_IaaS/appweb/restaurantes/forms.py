# -*- coding: utf-8 -*-
from django import forms

from .models import restaurants


class RestaurantesForm(forms.Form):
  nombre = forms.CharField(required=True, max_length=80)
  cocina = forms.CharField()
  dirección = forms.CharField()
  barrio = forms.CharField(widget=forms.TextInput())
