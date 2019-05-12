<template>
  <div class="hello">
    <PersonalMenu active="Home"></PersonalMenu>
    <div class="container">
      <div class="main-container">
        <div class="welcome-part">
          <p> 欢迎回家！ </p>
        </div>
        <div class="left">
          <div class="img-card">
            <div class="img">
              <img src="../../assets/logo.png">
            </div>
            <div class="upload">
              <Upload
                :action="upload_url"
                :format="['jpg', 'jpeg', 'png']"
                :show-upload-list="false"
                :before-upload="handleBeforeUpload"
                :on-format-error="handleFormatError">
                <Button icon="ios-cloud-upload-outline">选择文件</Button>
              </Upload>
              <div v-if="file !== null">
                选择文件名：{{file.name}}
                <Button type="text" @click="upload" :loading="loadingStatus"></Button>
              </div>
            </div>
            <div class="content">
              <p>Su Yunpeng</p>
              <div class="words">A Hefei Universi ty of Technology student.</div>
            </div>
          </div>
        </div>
        <div class="right">
          <div class="intro">
            <h1>About me</h1>
            <p v-for='item in desc' :key="item">{{item.intro}}</p>
          </div>
        </div>
        <div class="favorites">
          <h1>Favorites</h1>
          <img src="../../assets/django.png">
          <img src="../../assets/spring.jpeg">
          <img src="../../assets/vue.png">
          <img src="../../assets/starcraft.png">
        </div>
      </div>
      <MyFooter></MyFooter>
    </div>
  </div>
</template>

<script>
import PersonalMenu from '../common/PersonalMenu'
import MyFooter from '../common/footer'
export default {
  components: {PersonalMenu, MyFooter},
  name: 'home',
  mounted () {
    this.$axios
      .get('/index', {})
      .then(res => {
        console.log(res)
        if (res.data.code === 200) {
          this.desc = res.data.data
        } else {
          this.$Message.error('内部错误!')
        }
      })
  },
  data () {
    return {
      personalPhotoUrl: '',
      desc: [],
      file: null,
      loadingStatus: false,
      uploadUrl: 'http://localhost:8500/uploads'
    }
  },
  methods: {
    handleBeforeUpload (file) {
      this.file = file
      return false
    },
    upload () {
      let formData = new FormData()
      formData.append(this.file)
      this.$axios.post('/upload/personalPhoto', formData,
        {headers: {'Content-Type': 'multipart/form-data'}}
      )
        .then(res => {
          if (res.data.code === 200) {
            this.$Message.success('上传成功')
          } else {
            this.$Message.error('上传失败！')
          }
        })
        .catch(() => {
          this.$Message.error('服务器错误！')
        })
    },
    handleFormatError (file) {
      this.$Message.error('文件上传格式错误！')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.container {
  margin: 20px 0;
  width: 100%;
}

.main-container {
  position: relative;
  margin:0 auto;
  width: 80%;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}

.welcome-part {
  width: 100%;
  text-align: left;
  font-size: 20px;
  font-weight: bold;
  margin-left: 20px;
}

.left {
  width: 25%;
}

.right {
  width: 75%;
}

.intro {
  text-align: left;
  line-height: 35px;
  background: #fff;
  box-shadow: 0 1px 2px 0;
  margin: 20px 0px 20px 20px;
  border: 1px solid rgba(34,36,38,.15);
  border-radius: 5px;
  position: relative;
  flex-direction: column;
}

.intro > p {
  margin: 10px;
  font-size: 14px;
  color: #657180;
}

h1 {
  margin: 10px;
}

.img-card {
  box-shadow: 0 1px 2px 0;
  max-width: 100%;
  width: 290px;
  margin: 20px;
  border: 1px solid rgba(34,36,38,.15);
  border-radius: 5px;
  display: flex;
  position: relative;
  flex-direction: column;
}

.img {
  max-width: 100%;
  width: 100%;
  position: relative;
  display: block;
  flex: 0 0 auto;
  padding: 0;
  margin: 0;
}
.content {
  flex-grow: 1;
  border: none;
  border-top: 1px solid;
  background: white;
  margin: 0;
}

.content > p {
  font-size: 16px;
  color: #464c5b;
  font-weight: bold;
}

.words {
  font-size: 12px;
  color: #657180;
}

.favorites {
  width: 100%;
  text-align: left;
  background: #fff;
  box-shadow: 0 1px 2px 0;
  margin: 20px 0px 20px 20px;
  border: 1px solid rgba(34,36,38,.15);
  border-radius: 5px;
  position: relative;
}

.favorites > img {
  margin: 20px;
  height: 100px;
}
</style>
