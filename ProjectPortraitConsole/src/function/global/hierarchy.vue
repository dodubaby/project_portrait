<template>
  <div style="padding:30px; overflow: scroll">
    <el-alert :closable="false" type="info"
              title="Function说明"
              description="项目熟悉、风险范围评估、优先级评估"
              style="margin-bottom: 20px"/>
    <el-form ref="form" :model="form" label-width="120px" v-loading="loading">
      <el-form-item label="展示根节点">
        <el-input v-model="form.rootKey" style="width:300px;"/>
        <el-tag>空代表检索所有；示例：compass、libcore、global、dialog</el-tag>
      </el-form-item>
      <el-form-item label="Tag-Owner">
        <el-checkbox-group v-model="checkedList4Owner" size="mini">
          <el-checkbox-button v-for="data in dataList4Owner" :label="data.value" :key="data.type"></el-checkbox-button>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="Tag-Function">
        <el-checkbox-group v-model="checkedList4Function" size="mini">
          <el-checkbox-button v-for="data in dataList4Function" :label="data.value"
                              :key="data.type"></el-checkbox-button>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="Tag-Common">
        <el-checkbox-group v-model="checkedList4Common" size="mini">
          <el-checkbox-button v-for="data in dataList4Common" :label="data.value" :key="data.type"></el-checkbox-button>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="Tag-Other">
        <el-checkbox-group v-model="checkedList4Other" size="mini">
          <el-checkbox-button v-for="data in dataList4Other" :label="data.value" :key="data.type"></el-checkbox-button>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" plain @click="getHierarchy">查询</el-button>
        <el-button plain @click="onReset">重置所有选项</el-button>
      </el-form-item>
    </el-form>
    <div id="container"/>
    <tag-manage v-if="tagManageVisible" v-show="tagManageVisible" :data="data" :dataId="dataId" :dataType="dataType"
                @callBack="backToCurrentPage"></tag-manage>
  </div>
</template>
<script>
import {fileFindAll, tagFindAll} from '@/api/ppserver'
import TagManage from '../../components/function/tagManage'
import ElCol from "element-ui/packages/col/src/col";
import ElTag from "../../../node_modules/element-ui/packages/tag/src/tag";
import ElButton from "../../../node_modules/element-ui/packages/button/src/button";
import ElMain from "../../../node_modules/element-ui/packages/main/src/main";
import ElCheckboxGroup from "../../../node_modules/element-ui/packages/checkbox/src/checkbox-group";
import ElFormItem from "../../../node_modules/element-ui/packages/form/src/form-item";

export default {
  components: {
    ElFormItem, ElCheckboxGroup, ElMain,
    ElButton,
    ElTag,
    ElCol,
    'tag-manage': TagManage
  }, data() {
    return {
      form: {
        rootKey: 'global',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      loading: false,
      tagManageVisible: false,
      data: '',
      dataId: '',
      dataType: '',
      dataList4Owner: [],
      dataList4Function: [],
      dataList4Common: [],
      dataList4Other: [],
      checkedList4Owner: [],
      checkedList4Function: [],
      checkedList4Common: [],
      checkedList4Other: [],
    }
  },
  mounted() {
    this.getTagList()
  },
  methods: {
    onReset() {
      this.$message({
        message: 'reset',
        type: 'warning'
      })
    },
    showTagManageView(mData, mDataId, mDataType) {
      this.tagManageVisible = true
      this.data = mData
      this.dataId = mDataId
      this.dataType = mDataType
    },
    // 公共方法：从上一个vue返回到当前vue
    backToCurrentPage () {
      this.tagManageVisible = false
      this.$message('data update');
    },
    getTagList() {
      this.loading = true
      tagFindAll().then(response => {
        this.dataList4Owner = response.tagList4Owner
        this.dataList4Function = response.tagList4Function
        this.dataList4Common = response.tagList4Common
        this.dataList4Other = response.tagList4Other
        this.loading = false
      })
    },
    getHierarchy() {
      this.loading = true
      var tags = this.checkedList4Owner + "," + this.checkedList4Function + "," + this.checkedList4Common + "," + this.checkedList4Other
      fileFindAll('java', this.form.rootKey, tags).then(response => {
        var root = response.fileListWithHierarchy
        this.draw(root, this.showTagManageView);
        this.loading = false
      })
    },

    // ====D3.js start====
    // var root = {
    //   "dataName": "com",
    //   "children": [{
    //     "dataName": "111",
    //     "children": [{
    //       "dataName": "222",
    //       "children": [{
    //         "dataName": "333",
    //       }, {
    //         "dataName": "444",
    //       }, {
    //         "dataName": "555",
    //       }, {
    //         "dataName": "666",
    //       }]
    //     }]
    //   }, {
    //     "dataName": "ISchedulable"
    //   }, {
    //     "dataName": "Parallel",
    //     "children": [{
    //       "dataName": "111122222"
    //     }]
    //   }, {
    //     "dataName": "Pause",
    //   }]
    // };
    draw(root, nodeClick){
      var m = [20, 120, 20, 120],
        w = 10000 - m[1] - m[3],
        h = 1000 - m[0] - m[2],
        i = 0;
      var tree = d3.layout.tree()
        .size([h, w]);

      var diagonal = d3.svg.diagonal()
        .projection(function (d) {
          return [d.y, d.x];
        });

      // 清理历史视图
      d3.select("#container").select("svg").remove()
      // 添加新视图
      var vis = d3.select("#container").insert("svg:svg")
        .attr("width", w + m[1] + m[3])
        .attr("height", h + m[0] + m[2])
        .append("svg:g")
        .attr("transform", "translate(" + m[3] + "," + m[0] + ")");

      root.x0 = h / 2;
      root.y0 = 0;

      function toggleAll(d) {
        if (d.children) {
          d.children.forEach(toggleAll);
          toggle(d);
        }
      }

      // Initialize the display to show a few nodes.
      // root.children.forEach(toggleAll);
      // toggle(root.children[1]);
      // toggle(root.children[1].children[2]);
      // toggle(root.children[9]);
      // toggle(root.children[9].children[0]);

      update(root, nodeClick);

      function update(source, nodeClick) {
        var duration = d3.event && d3.event.altKey ? 5000 : 500;

        // Compute the new tree layout.
        var nodes = tree.nodes(root).reverse();

        // Normalize for fixed-depth.
        nodes.forEach(function (d) {
          d.y = d.depth * 180;
        });

        // Update the nodes…
        var node = vis.selectAll("g.node")
          .data(nodes, function (d) {
            return d.id || (d.id = ++i);
          });

        // Enter any new nodes at the parent's previous position.
        var nodeEnter = node.enter().append("svg:g")
          .attr("class", "node")
          .attr("transform", function (d) {
            return "translate(" + source.y0 + "," + source.x0 + ")";
          });

        nodeEnter.append("svg:circle")
          .attr("r", 1e-6)
          .style("fill", function (d) {
            return d._children ? "lightsteelblue" : "#fff";
          })
          .on("click", function (d) {
            toggle(d);
            update(d, nodeClick);
          });

        nodeEnter.append("svg:text")
          .attr("x", function (d) {
            return d.children || d._children ? -15 : 15;
          })
          .attr("dy", ".35em")
          .attr("text-anchor", function (d) {
            return d.children || d._children ? "end" : "start";
          })
          .text(function (d) {
            // 截取过长名称
            var str = d.dataName;
            if (str.length > 20) {
              str = str.substring(0, 20) + '..'
            }
            return str;
          })
          .on("click", function (d) {
            nodeClick(d.dataName, d.dataId, d.dataType)
          })
          .style("fill-opacity", 1e-6);
//        nodeEnter.append('svg:text')
//          .text('__')
//          .attr("x", function (d) {
//            return d.children || d._children ? -15 : 15;
//          })
//          .attr("dy", ".40em")
//          .attr("text-anchor", function (d) {
//            return d.children || d._children ? "end" : "start";
//          })
//          .style("fill", 'red');

        // Transition nodes to their new position.
        var nodeUpdate = node.transition()
          .duration(duration)
          .attr("transform", function (d) {
            return "translate(" + d.y + "," + d.x + ")";
          });

        nodeUpdate.select("circle")
          .attr("r", 8)
          .style("fill", function (d) {
            return d._children ? "lightsteelblue" : "#fff";
          });

        nodeUpdate.select("text")
          .style("fill-opacity", 1);

        // Transition exiting nodes to the parent's new position.
        var nodeExit = node.exit().transition()
          .duration(duration)
          .attr("transform", function (d) {
            return "translate(" + source.y + "," + source.x + ")";
          })
          .remove();

        nodeExit.select("circle")
          .attr("r", 1e-6);

        nodeExit.select("text")
          .style("fill-opacity", 1e-6);

        // Update the links…
        var link = vis.selectAll("path.link")
          .data(tree.links(nodes), function (d) {
            return d.target.id;
          });

        // Enter any new links at the parent's previous position.
        link.enter().insert("svg:path", "g")
          .attr("class", "link")
          .attr("d", function (d) {
            var o = {x: source.x0, y: source.y0};
            return diagonal({source: o, target: o});
          })
          .transition()
          .duration(duration)
          .attr("d", diagonal);

        // Transition links to their new position.
        link.transition()
          .duration(duration)
          .attr("d", diagonal);

        // Transition exiting nodes to the parent's new position.
        link.exit().transition()
          .duration(duration)
          .attr("d", function (d) {
            var o = {x: source.x, y: source.y};
            return diagonal({source: o, target: o});
          })
          .remove();

        // Stash the old positions for transition.
        nodes.forEach(function (d) {
          d.x0 = d.x;
          d.y0 = d.y;
        });
      }

      // Toggle children.
      function toggle(d) {
        if (d.children) {
          d._children = d.children;
          d.children = null;
        } else {
          d.children = d._children;
          d._children = null;
        }
      }
    }
    // ====D3.js end====
  }
}
</script>
<style>
  .node circle {
    cursor: pointer;
    fill: #fff;
    stroke: steelblue;
    stroke-width: 1.5px;
  }

  .node text {
    font-size: 14px;
  }

  path.link {
    fill: none;
    stroke: #ccc;
    stroke-width: 1.5px;
  }

  .line {
    text-align: center;
  }

  .el-form-item {
    margin-bottom: 0px;
  }
</style>
