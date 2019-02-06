#!/usr/bin/python3
# coding=utf-8

import ConfigParser

"""
获取配置内容
"""


def getConfig(configFile, sectionIndex, optionName):
    try:
        with open(configFile, 'r') as fr:
            cfg = ConfigParser.ConfigParser()
            cfg.readfp(fr)
            secs = cfg.sections()
            data = cfg.get(secs[sectionIndex], optionName)
            return data
    except:
        return ''
