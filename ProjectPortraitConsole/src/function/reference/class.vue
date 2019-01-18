<template>
  <div style="padding:30px; overflow: scroll">
    <el-alert :closable="false" type="info"
              title="视图说明"
              description="实线代表xxx，虚线代表xxx，绿色线代表xxx"
    />
    <div style="padding:20px;  ">
      <el-button size="big" @click="fetchData('RecommendView.java')" style="width: 250px">检索[RecommendView.java]
      </el-button>
      <el-button size="big" @click="fetchData('MessageUtils.java')" style="width: 250px">检索[MessageUtils.java]
      </el-button>
      <el-button size="big" @click="fetchData('SafeParseUtils.java')" style="width: 250px">检索[SafeParseUtils.java]
      </el-button>
      <el-button size="big" @click="fetchData('')" style="width: 250px">检索[所有java]</el-button>
    </div>

    <div id="container"/>
  </div>
</template>
<script>
import {referenceFindAll} from '@/api/ppserver'

export default {
  data() {
    return {}
  },
  mounted() {
    // this.fetchData()
  },
  methods: {
    fetchData(key) {
      referenceFindAll(key).then(response => {
        var links = response.referenceList
        this.draw(links);
      })
//      var links = [
//        {source: "哈哈哈哈哈哈哈", target: "Amazon", type: "licensing"},
//        {source: "Microsoft", target: "HTC", type: "licensing"},
//        {source: "Samsung", target: "Apple", type: "suit"},
//        {source: "Motorola", target: "哈哈哈哈哈哈哈", type: "suit"},
//        {source: "Nokia", target: "哈哈哈哈哈哈哈", type: "resolved"},
//        {source: "Kodak", target: "RIM", type: "suit"},
//        {source: "Nokia", target: "Qualcomm", type: "suit"},
//        {source: "aaa", target: "RIM", type: "suit"}
//      ];
    },

    draw(links){
      var nodes = {};

      // Compute the distinct nodes from the links.
      links.forEach(function (link) {
        link.source = nodes[link.source] || (nodes[link.source] = {name: link.source});
        link.target = nodes[link.target] || (nodes[link.target] = {name: link.target});
      });

      var width = 1500,
        height = 1500;

      var force = d3.layout.force()
        .nodes(d3.values(nodes))
        .links(links)
        .size([width, height])
        .linkDistance(60)
        .charge(-300)
        .on("tick", tick)
        .start();

      // 清理历史视图
      d3.select("#container").select("svg").remove()
      // 添加新视图
      var svg = d3.select("#container").insert("svg:svg")
        .attr("width", width)
        .attr("height", height);

      // Per-type markers, as they don't inherit styles.
      svg.append("defs").selectAll("marker")
        .data(["suit", "licensing", "resolved"])
        .enter().append("marker")
        .attr("id", function (d) {
          return d;
        })
        .attr("viewBox", "0 -5 10 10")
        .attr("refX", 15)
        .attr("refY", -1.5)
        .attr("markerWidth", 6)
        .attr("markerHeight", 6)
        .attr("orient", "auto")
        .append("path")
        .attr("d", "M0,-5L10,0L0,5");

      var path = svg.append("g").selectAll("path")
        .data(force.links())
        .enter().append("path")
        .attr("class", function (d) {
          return "link " + d.type;
        })
        .attr("marker-end", function (d) {
          return "url(#" + d.type + ")";
        });

      var circle = svg.append("g").selectAll("circle")
        .data(force.nodes())
        .enter().append("circle")
        .attr("r", 6)
        .call(force.drag);

      var text = svg.append("g").selectAll("text")
        .data(force.nodes())
        .enter().append("text")
        .attr("x", 8)
        .attr("y", ".31em")
        .text(function (d) {
          return d.name;
        });

      // Use elliptical arc path segments to doubly-encode directionality.
      function tick() {
        path.attr("d", linkArc);
        circle.attr("transform", transform);
        text.attr("transform", transform);
      }

      function linkArc(d) {
        var dx = d.target.x - d.source.x,
          dy = d.target.y - d.source.y,
          dr = Math.sqrt(dx * dx + dy * dy);
        return "M" + d.source.x + "," + d.source.y + "A" + dr + "," + dr + " 0 0,1 " + d.target.x + "," + d.target.y;
      }

      function transform(d) {
        return "translate(" + d.x + "," + d.y + ")";
      }
    }
  }
}
</script>
<style>
  .link {
    fill: none;
    stroke: #666;
    stroke-width: 1.5px;
  }

  #licensing {
    fill: green;
  }

  .link.licensing {
    stroke: green;
  }

  .link.resolved {
    stroke-dasharray: 0, 2 1;
  }

  circle {
    fill: #ccc;
    stroke: #333;
    stroke-width: 1.5px;
  }

  text {
    font: 12px sans-serif;
    pointer-events: none;
    text-shadow: 0 1px 0 #fff, 1px 0 0 #fff, 0 -1px 0 #fff, -1px 0 0 #fff;
  }
</style>
