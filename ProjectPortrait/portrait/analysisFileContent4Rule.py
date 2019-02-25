# encoding: utf-8
# !/usr/bin/python3

import regular.regular as regular
from db import db
from util.util import *

ruleList = None

"""
解析规则数据
"""


def analysisRuleData(fileId, lineContent, lineNum):
    if (ruleList is None):
        getRuleList()
    for rule in ruleList:
        key = rule[1]
        if(isNotEmpty(key)):
            ruleData = regular.regularWord(lineContent, key)
        else:
            ruleData = ""

        if (ruleData != ""):
            db.saveRuleData(rule[0], rule[5], fileId, lineContent, lineNum, 'normal')


"""
获取规则数据
"""


def getRuleList():
    global ruleList
    ruleList = db.readRuleList()
    print("检索到rule list共 %i 条" % len(ruleList))
    for rule in ruleList:
        print("检索到rule：" + str(rule))
