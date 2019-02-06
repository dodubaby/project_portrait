#!/usr/bin/python3
# coding=utf-8

"""
判空
"""


def isEmpty(data):
    if (data is None):
        return True
    if (data == 'None'):
        return True
    if (data == ''):
        return True
    return False


"""
判空
"""


def isNotEmpty(data):
    return not isEmpty(data)


def test():
    print
    # print isNotEmpty('s')


test()
