<template>
  <Menu mode="horizontal" theme="light" :active-name=active @on-select="navigateTo">
      <div class="logo">
          <Avatar icon="ios-person" size="large" />
          Su Yunpeng
      </div>
      <MenuItem name="Home" to=Home>
        <Icon type="ios-home" />
        Home
      </MenuItem>
      <MenuItem name="Projects" to=Projects>
        <Icon type="ios-code" />
        Projects
      </MenuItem>
      <MenuItem name="Education" to=Education>
        <Icon type="ios-school" />
        Education
      </MenuItem>
      <div class="rightItem">
        <MenuItem name="1">
          我是我自己？请登录
        </MenuItem>
        <MenuItem name="2">
          <Icon type="logo-github" size="24"/>
        </MenuItem>
        <MenuItem name="3">
          blog
        </MenuItem>
      </div>
  </Menu>
</template>

<script>
export default {
  props: ['active'],
  name: 'PersonalMenu',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods: {
    navigateTo (name) {
      switch (name) {
        case '1':
          this.$router.push({path: '/login'})
          break
        case '2':
          window.open('https://github.com/lyfxxxx')
          break
        case '3':
          window.open('https://mp.csdn.net/')
          break
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.logo {
    width: 200px;
    background: white;
    position: relative;
    float: left;
}
.rightItem {
  float: right;
  position: relative;
}
</style>
