# encoding: utf-8
# !/usr/bin/python3

import regular.regular as regular
from db import db
from util.util import *

ruleList = None

"""
解析规则数据
"""


def analysisRuleData(fileId, lineContent, lineNum, suffix):
    if (ruleList is None):
        getRuleList()
    for rule in ruleList:
        scan_file_suffix = rule[2]
        if scan_file_suffix not in suffix:
            continue
        key = rule[1]
        if (isEmpty(key)):
            continue
        ruleData = regular.regularWord(lineContent, key)
        if (isEmpty(ruleData)):
            continue
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
