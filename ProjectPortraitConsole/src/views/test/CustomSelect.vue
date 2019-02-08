<template>
  <div class="wrap">
    <div class="searchIpt clearFix">
      <div class="clearFix">
        <input type="text" class="KeyWord" :value="inputValue" @click="showList">
        <input type="button" :value="btnTitle">
        <span></span>
      </div>
      {{
      list
      }}


    </div>
  </div>
</template>

<script>
export default {
  name: 'CustomSelect',
  props: ['list', 'btnTitle'],
  data () {
    return {
      selectShow: false,
      inputValue: ''
    }
  },
  methods: {
    showList () {
      this.selectShow = !this.selectShow
      this.$emit('receiveValue', 111)
    },
    changeValueHandle (value) {
      this.inputValue = value
    }
  }
}
</script>
