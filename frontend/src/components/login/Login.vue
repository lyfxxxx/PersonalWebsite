<template>
  <div class="login">
    <PersonalMenu></PersonalMenu>
    <div class="form">
      <p>登录</p>
      <Form ref="loginForm" :model="loginForm" :rules="loginRules">
        <FormItem prop="name">
          <Input type="text" v-model="loginForm.name" placeholder="输入用户名" />
        </FormItem>
        <FormItem prop="password">
          <Input type="text" v-model="loginForm.password" placeholder="输入密码" />
        </FormItem>
        <FormItem>
          <Button type="primary" @click="handleSubmit('loginForm')" >登录</Button>
        </FormItem>
      </Form>
    </div>
  </div>
</template>

<script>
import PersonalMenu from '../common/PersonalMenu'
export default {
  components: {PersonalMenu},
  name: 'login',
  data () {
    return {
      loginForm: {
        name: '',
        password: ''
      },
      loginRules: {
        name: [
          {required: true, message: '请输入用户名！', trigger: 'blur'}
        ],
        password: [
          {required: true, type: 'string', message: '请输入密码！', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    handleSubmit (name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.$axios({
            url: '/login',
            method: 'post',
            data: this.loginForm
          }).then(res => {
            if (res.data.code === 200) {
              this.$router.replace({path: '/loginhome'})
            } else if (res.data.code === 400) {
              this.$Message.error('用户名不存在或密码错误！')
            }
          }).catch(err => {
            this.$Message.error('内部错误！')
            console.info('errMsg', err.response.message)
          })
        } else {
          this.$Message.error('请检查表单！')
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login {
  width: 100%;
  height: 400px;
}
.form {
  margin: auto;
  margin-top: 50px;
  width: 40%;
  border-radius: 5px;
  border: 1px solid rgba(34,36,38,.15);
  box-shadow: 0 1px 2px 0;
  background: #fff;
  padding: 20px 100px;
}

.form > p {
  font-size: 14px;
  font-weight: bold;
  text-align: left;
  margin-bottom: 20px;
}
</style>
