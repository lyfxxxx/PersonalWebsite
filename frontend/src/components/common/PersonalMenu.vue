<template>
  <Menu mode="horizontal" theme="primary" active-name="1" @on-select="navigateTo">
      <div class="logo">
          <Avatar icon="ios-person" size="large" />
          Su Yunpeng
      </div>
      <MenuItem name="Home">
        <Icon type="ios-home" />
        Home
      </MenuItem>
      <MenuItem name="Projects">
        <Icon type="ios-code" />
        Projects
      </MenuItem>
      <MenuItem name="Eduction">
        <Icon type="ios-school" />
        Eduction
      </MenuItem>
      <div class="rightItem">
        <MenuItem name="Github">
          <Icon type="logo-github" />
        </MenuItem>
        <MenuItem name="blog">
          blog
        </MenuItem>
      </div>
  </Menu>
</template>

<script>
export default {
  name: 'PersonalMenu',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods: {
    navigateTo (name) {
      this.$router.push(name)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.logo {
    width: 100px;
    background: white;
    position: relative;
    float: left;
}
</style>
