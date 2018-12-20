#!/usr/bin/python3
# coding=utf-8

import datetime


def timeStart():
    startTime = datetime.datetime.now()
    print ("========== task start: time: " + str(startTime))
    return startTime


def timeEnd(startTime):
    endTime = datetime.datetime.now()
    print ("========== task end: time: " + str(endTime))
    totalTime = (endTime - startTime).seconds
    print ("========== task use time: " + str(totalTime) + "s")
