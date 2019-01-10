# project_portrait
项目画像（分析）工具


## ProjectPortrait-原始数据采集系统
通过文本分析、语义分析，对项目源码进行整体数据采集。相关采集数据统一入库，供二级系统调用

【技术方案：Python】


## ProjectPortraitServer-数据整合系统及输出能力提供者
数据服务api提供者，对原始数据进行多维度处理和整合，二级系统可通过指定api，获取期望数据集

【技术方案：Java、Mybatis、Spring等】


## ProjectPortraitConsole-系统控制台、数据展示门户
获取数据整合系统提供的数据，并通过可视化展示方案进行展示处理，同时支持通过页面操作进行系统控制

【技术方案：大前端框架Vue、图表绘制D3.js、网络框架axios等】


## Doc
相关文档说明、附件

------------


## 源码及链接
- https://github.com/laoshubuluo/project_portrait
- http://localhost:9528/#/dashboard
- http://localhost:8080/pp/request?actionId=1002&suffix=java


## 痛点
对于多年维护的旧项目
#### 决策者
1. 无法搞清项目到底有哪些问题，多少问题
2. 很难搞清楚要做的事情（项目优化、技术更新）的优先级
3. 很难清楚的了解要调整涉及的范围、风险
4. 无法实时准确的掌握人员与功能分配情况，从而也很难合理进行新的分配
5. 新代码合规性无法及时验证


#### Coder
1. 无法检索多层引用
2. 无法确定优化改变带来的工作量及风险
3. 无法定制化检索（查询特定规则的数据、查询多规则的数据）（混淆查找所有bean、所有UIcode、有Router没有兼容参数）
4. 找不到前人造的轮子
5. 想优化下不了手，没法坐在一起交流讨论
6. Wiki文档等对项目的管理和说明，无法实时更新，经常烂尾


#### 新人
1. 无法快速了解项目情况
2. 无法快速接入公共模块、公共类等


## 宗旨与目的
- 可用性、舒适性：不为做功能而做功能，功能高效率强便捷
- 功能独有：与现有各种检测工具、分析工具，功能不交叉

- 开放出去，真正为Android、iOS 开发者所用


## 难点
1. 多平台切换开发
2. 语义分析是基础，如何将规律抽取成模型，小成本开放给更多场景及IOS平台
3. 项目可展示到真正可用的提升（涉及功能合理性、操作交互等等）


## 待实现功能
略


## 其他
求战友~三个系统涉及的技术，感兴趣都可以


## 周边工具参考
略

