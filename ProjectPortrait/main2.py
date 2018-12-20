# encoding: utf-8
#!/usr/bin/python3

import main1


def test(str):
    print(str)


print(test('hello'))

print('-------------')


def changeValue(a):
    print('改变中')
    a = 1


a = 2
print(a)
changeValue(a)
print(a)

print('-------------')


def changeValue1(a):
    print('改变中')
    a[1] = 1


a = ['2', '2', '2']
print(a)
changeValue1(a)
print(a)

print('-------------')
print(main1.a)

print('{name}哈哈哈哈：{site}'.format(name='aaaaa', site='bbbb'))

print('-------------')
print('请输入密码：')
key = input('开始输入：')
print('-------------')
print('你输入的密码是：', key * 3)
