<template>
  <div class="hello">
    <LoginMenu></LoginMenu>
    <div class="main-container">
      <div class="intro-edit-card">
        <h1>修改个人简介</h1>
        <div class="intro">
          <div class="line" v-for='(item, index) in desc' :key="index" >
            <div class="no-edit" v-if="item.isEdit === false">
              <p>{{item.intro}}</p>
              <div class="button">
                <Button type="info" @click="handleChange(item)" size="small">修改</Button>
                <Button type="error" @click="handleDelete(item, index)" size="small">删除</Button>
              </div>
            </div>
            <div class="edit" v-else>
              <Input v-model="item.intro" :maxlength="200" type="textarea" @on-blur="handleBlur(item)"/>
              <div class="button">
                <Button type="info" @click="handleUpdate(item)" size="small">保存</Button>
                <Button type="warning" @click="handleCancel(item, index)" size="small">取消</Button>
              </div>
            </div>
          </div>
        </div>
        <div class="add">
          <Button type="primary" icon="md-add" @click="handleAdd" size="large">添加段落</Button>
        </div>
      </div>
      <MyFooter></MyFooter>
    </div>
  </div>
</template>

<script>
import LoginMenu from '../login/LoginMenu'
import MyFooter from '../common/footer'
export default {
  components: {LoginMenu, MyFooter},
  name: 'introeditor',
  mounted () {
    this.$axios
      .get('/getEditInfo', {})
      .then(res => {
        if (res.data.code === 200) {
          this.desc = res.data.data
          for (let i = 0; i < this.desc.length; i++) {
            this.backupDesc.push(this.desc[i].intro)
            this.desc[i]['isEdit'] = false
            this.desc[i]['isAdd'] = false
            this.desc[i]['isPass'] = false
          }
        } else {
          this.$Message.error('内部错误!')
        }
      })
  },
  data () {
    return {
      desc: [],
      backupDesc: []
    }
  },
  methods: {
    handleChange (item) {
      item.isEdit = true
      this.desc.push({})
      this.desc.pop()
    },
    handleCancel (item, index) {
      if (item.isAdd) {
        this.desc.splice(index, 1)
      } else {
        item.isEdit = false
        item.intro = this.backupDesc[index]
        this.desc.push({})
        this.desc.pop()
      }
    },
    handleBlur (item) {
      if (item.intro.length > 0 && item.intro.length < 200 && !item.intro.match(/^[ ]*$/)) {
        item.isPass = true
      }
    },
    handleDelete (item, index) {
      this.$axios({
        url: '/deleteInfo',
        method: 'post',
        data: item
      }).then(res => {
        if (res.data.code === 200) {
          this.$Message.success('删除成功')
          this.desc.splice(index, 1)
        } else {
          this.$Message.error('删除失败！')
        }
      }).catch(() => {
        this.$Message.error('内部错误！')
      })
    },
    handleUpdate (item) {
      if (item.isPass) {
        if (item.isAdd === false) {
          this.$axios({
            url: '/updateInfo',
            method: 'post',
            data: item
          }).then(res => {
            if (res.data.code === 200) {
              this.$Message.success('修改成功')
              item.isEdit = false
              item.isPass = false
              this.desc.push({})
              this.desc.pop()
            } else {
              this.$Message.error('修改失败！')
            }
          }).catch(() => {
            this.$Message.error('内部错误！')
          })
        } else {
          let formData = new FormData()
          formData.append('newInfo', item.intro)
          this.$axios.post('/insertNewInfo', formData,
            {headers: {'Content-Type': 'multipart/form-data'}}
          )
            .then(res => {
              if (res.data.code === 200) {
                this.$Message.success('添加成功')
                this.backupDesc.push(item.intro)
                item.isEdit = false
                item.isPass = false
                item.isAdd = false
                this.desc.push({})
                this.desc.pop()
              } else {
                this.$Message.error('添加失败！')
              }
            })
            .catch(() => {
              this.$Message.error('服务器错误！')
            })
        }
      } else {
        this.$Message.warning('请检查输入框！内容不能为空且最多200字')
      }
    },
    handleAdd () {
      this.desc.push({
        isEdit: true,
        isAdd: true,
        intro: ''
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.main-container {
  margin: 20px 0;
  width: 100%;
}

.intro-edit-card {
  margin: 0 auto;
  position: relative;
  width: 80%;
  max-width: 100%;
  display: flex;
  flex-direction: column;
  border-radius: 5px;
  border: 1px solid rgba(34,36,38,.15);
  box-shadow: 0 1px 2px 0;
  background: #fff;
}

.intro-edit-card > h1 {
  margin: 10px;
  text-align: left;
}

.intro {
  text-align: left;
  line-height: 35px;
  background: #fff;
  box-shadow: 0 1px 2px 0;
  margin: 20px;
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

#time {
  text-align: right;
  margin: 10px;
  font-size: 14px;
}

.education > p {
  font-size: 16px;
  text-align: left;
  margin: 10px;
  color: #657180;
}

.no-edit {
  margin: 20px;
  border: none;
  border-top: 1px solid;
}

.edit {
  margin: 20px;
  border: none;
  border-top: 1px solid;
}

.button {
  text-align: center;
}

.add {
  margin: 20px;
  text-align: center;
}
</style>
