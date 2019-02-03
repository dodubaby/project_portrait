# encoding: utf-8
# !/usr/bin/python3

from db import db

"""
解析规则数据
"""

ruleList = None


def getRuleList():
    global ruleList
    ruleList = db.readRuleList()
    print("查询到rule list: " + str(ruleList))
    for rule in ruleList:
        ruleId = str(rule[0])
        ruleXX = str(rule[1])
        print("xxxxxx aaaaaa rule: " + ruleXX)


def analysisRuleData(fileId, lineContent, lineNum):
    if (ruleList is None):
        getRuleList()

    for rule in ruleList:
        ruleId = str(rule[0])
        ruleXX = str(rule[1])
        print("xxxxxx rule: " + ruleXX)
