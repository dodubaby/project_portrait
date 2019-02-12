<template>
  <div class="box">
    <div class="wrap">
      <h1>城市列表</h1>
      <custom-select :list="citys" btnTitle="Searcheedddddd"></custom-select>
    </div>
    <div class="wrap">
      <h1>技能列表</h1>
      <custom-select v-if="visible" :list="skills" btnTitle="Search" @receiveValue="aaaa"></custom-select>
    </div>
  </div>
</template>

<script>
import CustomSelect from './CustomSelect'
export default {
  components: {
    'name': 'SelectPage',
    'custom-select': CustomSelect
  },
  data () {
    return {
      citys: ['北京', '上海', '广州', '青岛'],
      skills: ['Java', 'Android', 'Vue', 'PHP'],
      visible: true
    }
  },
  methods: {
    aaaa (abc) {
      this.visible = false
    }
  }
}
</script>
