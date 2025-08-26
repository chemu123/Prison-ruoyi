<template>
  <div style="padding: 30px;">
    <!-- 折叠面板 -->
    <el-collapse v-model="activeName" accordion>
      <el-collapse-item title="五星级牢笼体验" name="1">
        <div style="color: blueviolet;">这里的‘套房’配备了最坚固的门锁，保证无人打扰的私人空间，而且‘客房服务’每天准时三次，从不错过。</div>
        <!-- <div>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</div> -->
      </el-collapse-item>
      <el-collapse-item title="时间管理大师养成所" name="2">
        <div style="color: blueviolet;">在这里，你将学会如何与日出日落同步作息，彻底改掉熬夜的坏习惯，成为时间管理的高手。</div>
        <!-- <div>页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。</div> -->
      </el-collapse-item>
      <el-collapse-item title="健身与哲学并重的修身之地" name="3">
        <div style="color: blueviolet;">每日例行的‘户外活动’不仅增强了身体素质，也让许多人在仰望四角天空时悟出了人生的真谛。</div>
        <!-- <div>清晰明确：语言表达清晰且表意明确，让用户快速理解进而作出决策；</div> -->
        <!-- <div>帮助用户识别：界面简单直白，让用户快速识别而非回忆，减少用户记忆负担。</div> -->
      </el-collapse-item>
      <el-collapse-item title="全天候安保服务" name="4">
        <div style="color: blueviolet;">无需担心安全问题，这里提供24小时贴身保护，连蚊子都难以飞进来，安全感满满当当。</div>
        <!-- <div>结果可控：用户可以自由的进行操作，包括撤销、回退和终止当前操作等。</div> -->
      </el-collapse-item>
      <el-collapse-item title="独一无二的制服文化" name="5">
        <div style="color: blueviolet;">没有时尚潮流的困扰，每个人都身着统一的‘制服’，真正实现了‘人穿衣’而非‘衣穿人’的朴素美学。</div>
        <!-- <div>结果可控：用户可以自由的进行操作，包括撤销、回退和终止当前操作等。</div> -->
      </el-collapse-item>
    </el-collapse>

    <div style="width: 100%;">
      <!-- 饼图 -->
     <div id="bing" style="width:50%;height:400px;"></div>
    </div>
  </div>
</template>

<script>
import { listCells } from "@/api/prisoner/cells";
// import { listInmates} from "@/api/prisoner/inmates";
import * as echarts from 'echarts';
// import * as echarts from 'echarts/core';
 export default {
    data() {
      return {
        activeName: '1',
        // 饼图
        aqu:1,
        bqu:0,
        cqu:0,
        option:{
          title: {
            text: '监区分布',
            subtext: '',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            orient: 'vertical',
            left: 'left'
          },
          series: [
            {
              name: '囚犯数量',
              type: 'pie',
              radius: '50%',
              data: [
                { value: 3, name: 'A区' },
                { value: 2, name: 'B区' },
                { value: 4, name: 'C区' },
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        },
        // 饼图
        bingtuxinxi:[],
        // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 50,
        firstName: null,
      },
      };
    },
    mounted() {
      this.getList();
      setTimeout(() => {
        this.bingtu();
      }, 100);
    },
    // beforeUpdate(){this.bingtu();},
    methods: {
      getList() {
      this.loading = true;
      listCells(this.queryParams).then(response => {
        this.bingtuxinxi = response.rows;
        this.loading = false;
      });
    },
      bingtu() {
        // this.aqu=2
        for(const item of this.bingtuxinxi) {
          // console.log(item.blockNumber)
          if(item.blockNumber==0){this.aqu++}
          if(item.blockNumber==1){this.bqu++}
          if(item.blockNumber==2){this.cqu++}
        }
        // console.log(this.aqu);
        this.option.series[0].data[0].value=this.aqu
        this.option.series[0].data[1].value=this.bqu
        this.option.series[0].data[2].value=this.cqu
        var chartDom = document.getElementById('bing');
        var myChart = echarts.init(chartDom);
        this.option && myChart.setOption(this.option);
      },
    }
  }
</script>

<style>

</style>