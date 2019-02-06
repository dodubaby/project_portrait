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
        keyLeft = str(rule[1])
        keyRight = str(rule[2])
        # 双边界
        if (isNotEmpty(keyLeft) and isNotEmpty(keyRight)):
            ruleData = regular.regular(lineContent, keyLeft, keyRight)
        # 左边界
        elif (isNotEmpty(keyLeft)):
            ruleData = regular.regularWithLeft(lineContent, keyLeft)
        # 右边界
        elif (isNotEmpty(keyRight)):
            ruleData = regular.regularWithRight(lineContent, keyRight)
        else:
            ruleData = ""

        if (ruleData != ""):
            db.saveRuleData(rule[0], fileId, lineContent, lineNum)


"""
获取规则数据
"""


def getRuleList():
    global ruleList
    ruleList = db.readRuleList()
    print("检索到rule list共 %i 条" % len(ruleList))
    for rule in ruleList:
        print("检索到rule：" + str(rule))
