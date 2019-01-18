<template>
  <div style="padding:30px; overflow: scroll">
    <el-alert :closable="false" type="info"
              title="视图说明"
              description="项目熟悉、风险范围评估、优先级评估"/>

    <el-form ref="form" :model="form" label-width="120px" style="margin-top: 20px;">
      <el-form-item label="展示根节点">
        <el-input v-model="form.rootKey" style="width:300px;"/>
        <el-tag>空代表检索所有</el-tag>
      </el-form-item>
      <el-form-item label="Tag-owner">
        <el-checkbox-group v-model="form.type">
          <el-checkbox label="item1" name="type"/>
          <el-checkbox label="item2" name="type"/>
          <el-checkbox label="item3" name="type"/>
          <el-checkbox label="item4" name="type"/>
          <el-checkbox label="item5" name="type"/>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="Tag-function">
        <el-checkbox-group v-model="form.type">
          <el-checkbox label="item6" name="type"/>
          <el-checkbox label="item7" name="type"/>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="Tag-other">
        <el-checkbox-group v-model="form.type">
          <el-checkbox label="item8" name="type"/>
          <el-checkbox label="item9" name="type"/>
          <el-checkbox label="item10" name="type"/>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" plain @click="fetchData">查询</el-button>
        <el-button plain @click="onReset">重置所有选项</el-button>
      </el-form-item>
    </el-form>
    <div id="container"/>
  </div>
</template>
<script>
import {fileFindAll} from '@/api/ppserver'
import ElCol from "element-ui/packages/col/src/col";
import ElTag from "../../../node_modules/element-ui/packages/tag/src/tag";
import ElButton from "../../../node_modules/element-ui/packages/button/src/button";

export default {
  components: {ElButton, ElTag, ElCol}, data() {
    return {
      form: {
        rootKey: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  mounted() {
  },
  methods: {
    onReset() {
      this.$message({
        message: 'reset',
        type: 'warning'
      })
    },
    fetchData() {
      fileFindAll(this.form.rootKey).then(response => {
        var root = response.fileListWithHierarchy
        this.draw(root);
      })
//      var root = {
//        "name": "com",
//        "children": [{
//          "name": "111",
//          "children": [{
//            "name": "222",
//            "children": [{
//              "name": "333",
//            }, {
//              "name": "444",
//            }, {
//              "name": "555",
//            }, {
//              "name": "666",
//            }]
//          }]
//        }, {
//          "name": "ISchedulable"
//        }, {
//          "name": "Parallel",
//          "children": [{
//            "name": "111122222"
//          }]
//        }, {
//          "name": "Pause",
//        }]
//      };
    },

    draw(root){
      var m = [20, 120, 20, 120],
        w = 3000 - m[1] - m[3],
        h = 1000 - m[0] - m[2],
        i = 0;
      var tree = d3.layout.tree()
        .size([h, w]);

      var diagonal = d3.svg.diagonal()
        .projection(function (d) {
          return [d.y, d.x];
        });

      var vis = d3.select("#container").append("svg:svg")
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

      update(root);


      function update(source) {
        var duration = d3.event && d3.event.altKey ? 5000 : 500;

        // Compute the new tree layout.
        var nodes = tree.nodes(root).reverse();

        // Normalize for fixed-depth.
        nodes.forEach(function (d) {
          d.y = d.depth * 250;
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
          })
          .on("click", function (d) {
            toggle(d);
            update(d);
          });

        nodeEnter.append("svg:circle")
          .attr("r", 1e-6)
          .style("fill", function (d) {
            return d._children ? "lightsteelblue" : "#fff";
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
            return d.name + "[t.u]";
          })
          .on("click", function (d) {
            alert('处理归属者标签、功能标签、其他标签\n查找引用关系\nIDE中打开文件');
          })
          .style("fill-opacity", 1e-6);
        nodeEnter.append('svg:text')
          .text('__')
          .attr("x", function (d) {
            return d.children || d._children ? -15 : 15;
          })
          .attr("dy", ".40em")
          .attr("text-anchor", function (d) {
            return d.children || d._children ? "end" : "start";
          })
          .style("fill", 'red');

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
</style>
