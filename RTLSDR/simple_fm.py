#!/usr/bin/env python
##################################################
# Gnuradio Python Flow Graph
# Title: Simple Fm
# Generated: Thu Jun 21 23:14:30 2012
##################################################

from gnuradio import audio
from gnuradio import blks2
from gnuradio import eng_notation
from gnuradio import gr
from gnuradio import window
from gnuradio.eng_option import eng_option
from gnuradio.gr import firdes
from gnuradio.wxgui import fftsink2
from gnuradio.wxgui import forms
from grc_gnuradio import wxgui as grc_wxgui
from optparse import OptionParser
import baz
import wx

class simple_fm(grc_wxgui.top_block_gui):

	def __init__(self):
		grc_wxgui.top_block_gui.__init__(self, title="Simple Fm")

		##################################################
		# Variables
		##################################################
		self.frequency = frequency = 101500000
		self.volume = volume = 2
		self.samp_rate = samp_rate = 1400000
		self.freq = freq = frequency
		self.bandwidth = bandwidth = 1500000

		##################################################
		# Blocks
		##################################################
		_volume_sizer = wx.BoxSizer(wx.VERTICAL)
		self._volume_text_box = forms.text_box(
			parent=self.GetWin(),
			sizer=_volume_sizer,
			value=self.volume,
			callback=self.set_volume,
			label="Volume",
			converter=forms.float_converter(),
			proportion=0,
		)
		self._volume_slider = forms.slider(
			parent=self.GetWin(),
			sizer=_volume_sizer,
			value=self.volume,
			callback=self.set_volume,
			minimum=1,
			maximum=10,
			num_steps=1000,
			style=wx.SL_HORIZONTAL,
			cast=float,
			proportion=1,
		)
		self.Add(_volume_sizer)
		self.wxgui_fftsink2_0 = fftsink2.fft_sink_c(
			self.GetWin(),
			baseband_freq=freq,
			y_per_div=10,
			y_divs=10,
			ref_level=20,
			ref_scale=2.0,
			sample_rate=samp_rate,
			fft_size=1024,
			fft_rate=15,
			average=False,
			avg_alpha=None,
			title="Plot",
			peak_hold=False,
		)
		self.Add(self.wxgui_fftsink2_0.win)
		self.rtl2832_source_0 = baz.rtl_source_c(defer_creation=True, output_size=gr.sizeof_gr_complex)
		self.rtl2832_source_0.set_verbose(True)
		self.rtl2832_source_0.set_vid(0x0)
		self.rtl2832_source_0.set_pid(0x0)
		self.rtl2832_source_0.set_tuner_name("")
		self.rtl2832_source_0.set_default_timeout(0)
		self.rtl2832_source_0.set_use_buffer(True)
		self.rtl2832_source_0.set_fir_coefficients(([]))
		
		self.rtl2832_source_0.set_read_length(0)
		
		
		
		
		if self.rtl2832_source_0.create() == False: raise Exception("Failed to create RTL2832 Source: rtl2832_source_0")
		
		self.rtl2832_source_0.set_bandwidth(bandwidth)
		
		self.rtl2832_source_0.set_sample_rate(samp_rate)
		
		self.rtl2832_source_0.set_frequency(freq)
		
		
		
		self.rtl2832_source_0.set_auto_gain_mode(False)
		self.rtl2832_source_0.set_relative_gain(True)
		self.rtl2832_source_0.set_gain(20)
		  
		self.low_pass_filter_0 = gr.fir_filter_ccf(4, firdes.low_pass(
			2, samp_rate, 25000, 50000, firdes.WIN_HAMMING, 6.76))
		self.gr_multiply_const_vxx_0 = gr.multiply_const_vff((volume, ))
		_frequency_sizer = wx.BoxSizer(wx.VERTICAL)
		self._frequency_text_box = forms.text_box(
			parent=self.GetWin(),
			sizer=_frequency_sizer,
			value=self.frequency,
			callback=self.set_frequency,
			label="Frequenz",
			converter=forms.float_converter(),
			proportion=0,
		)
		self._frequency_slider = forms.slider(
			parent=self.GetWin(),
			sizer=_frequency_sizer,
			value=self.frequency,
			callback=self.set_frequency,
			minimum=90000000,
			maximum=110000000,
			num_steps=1000,
			style=wx.SL_HORIZONTAL,
			cast=float,
			proportion=1,
		)
		self.Add(_frequency_sizer)
		self.blks2_wfm_rcv_0 = blks2.wfm_rcv(
			quad_rate=samp_rate,
			audio_decimation=8,
		)
		self.audio_sink_0 = audio.sink(44100, "", True)

		##################################################
		# Connections
		##################################################
		self.connect((self.low_pass_filter_0, 0), (self.blks2_wfm_rcv_0, 0))
		self.connect((self.rtl2832_source_0, 0), (self.wxgui_fftsink2_0, 0))
		self.connect((self.rtl2832_source_0, 0), (self.low_pass_filter_0, 0))
		self.connect((self.blks2_wfm_rcv_0, 0), (self.gr_multiply_const_vxx_0, 0))
		self.connect((self.gr_multiply_const_vxx_0, 0), (self.audio_sink_0, 0))

	def get_frequency(self):
		return self.frequency

	def set_frequency(self, frequency):
		self.frequency = frequency
		self.set_freq(self.frequency)
		self._frequency_slider.set_value(self.frequency)
		self._frequency_text_box.set_value(self.frequency)

	def get_volume(self):
		return self.volume

	def set_volume(self, volume):
		self.volume = volume
		self.gr_multiply_const_vxx_0.set_k((self.volume, ))
		self._volume_slider.set_value(self.volume)
		self._volume_text_box.set_value(self.volume)

	def get_samp_rate(self):
		return self.samp_rate

	def set_samp_rate(self, samp_rate):
		self.samp_rate = samp_rate
		self.rtl2832_source_0.set_sample_rate(self.samp_rate)
		self.wxgui_fftsink2_0.set_sample_rate(self.samp_rate)
		self.low_pass_filter_0.set_taps(firdes.low_pass(2, self.samp_rate, 25000, 50000, firdes.WIN_HAMMING, 6.76))

	def get_freq(self):
		return self.freq

	def set_freq(self, freq):
		self.freq = freq
		self.rtl2832_source_0.set_frequency(self.freq)
		self.wxgui_fftsink2_0.set_baseband_freq(self.freq)

	def get_bandwidth(self):
		return self.bandwidth

	def set_bandwidth(self, bandwidth):
		self.bandwidth = bandwidth
		self.rtl2832_source_0.set_bandwidth(self.bandwidth)

if __name__ == '__main__':
	parser = OptionParser(option_class=eng_option, usage="%prog: [options]")
	(options, args) = parser.parse_args()
	tb = simple_fm()
	tb.Run(True)

