<template>
  <div class="hello">
    <LoginMenu></LoginMenu>
    <div class="main-container">
      <div class="projects-card">
        <h1>编辑项目经历</h1>
        <div class="project-edit">
          <Form ref="ProjEditForm" :model="ProjEditForm" :rules="ProjRules" label-position="top">
            <FormItem label="项目名称" prop="prjname">
              <Input v-model="ProjEditForm.prjname" placeholder="请输入项目名称" />
            </FormItem>
            <FormItem label="项目职位" prop="pos">
              <Input v-model="ProjEditForm.pos" placeholder="请输入项目职位" />
            </FormItem>
            <FormItem label="项目简介" prop="intro">
              <Input v-model="ProjEditForm.intro" placeholder="请输入项目简介" type="textarea" :rows="4"/>
            </FormItem>
            <FormItem label="项目职责" prop="duty">
              <Input v-model="ProjEditForm.duty" placeholder="请输入项目职责" type="textarea" :rows="4"/>
            </FormItem>
            <FormItem label="起始年月">
              <Row>
                <Col span="12" class="colClass">
                  <FormItem prop="startYear" >
                    <Select v-model="ProjEditForm.startYear" placeholder="选择起始年份">
                      <Option v-for="(i, index) in years" :key="index" :value="i">{{i}}</Option>
                    </Select>
                  </FormItem>
                </Col>
                <Col span="12">
                  <FormItem prop="startMonth">
                    <Select v-model="ProjEditForm.startMonth" placeholder="选择起始月份">
                      <Option v-for="(j, index) in months" :key="index" :value="j">{{j}}</Option>
                    </Select>
                  </FormItem>
                </Col>
              </Row>
            </FormItem>
            <FormItem label="结束年月">
              <Row>
                <Col span="12" class="colClass">
                  <FormItem prop="endYear">
                    <Select v-model="ProjEditForm.endYear" placeholder="选择结束年份">
                      <Option v-for="(k, index) in years" :key="index" :value="k">{{k}}</Option>
                    </Select>
                  </FormItem>
                </Col>
                <Col span="12">
                  <FormItem prop="endMonth">
                    <Select v-model="ProjEditForm.endMonth" placeholder="选择结束月份">
                      <Option v-for="(l, index) in months" :key="index" :value="l">{{l}}</Option>
                    </Select>
                  </FormItem>
                </Col>
              </Row>
            </FormItem>
            <div class="button">
              <FormItem>
                <Button type="primary" @click="handleSubmit('ProjEditForm')">保存</Button>
                <Button type="warning" @click="handleCancel">取消</Button>
              </FormItem>
            </div>
          </Form>
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
  name: 'projedit',
  mounted () {
    for (let i = 2010; i < 2030; i++) {
      this.years.push(String(i))
    }
    let formData = new FormData()
    formData.append('id', this.$route.query.id)
    this.$axios.post('/project', formData,
      {headers: {'Content-Type': 'multipart/form-data'}}
    )
      .then(res => {
        if (res.data.code === 200) {
          this.sendProj.id = this.$route.query.id
          this.ProjEditForm.prjname = res.data.data.prjname
          this.ProjEditForm.intro = res.data.data.intro
          this.ProjEditForm.duty = res.data.data.duty
          this.ProjEditForm.pos = res.data.data.pos
          let time = res.data.data.time
          let startTime = time.split('-')[0]
          let endTime = time.split('-')[1]
          this.ProjEditForm.startYear = startTime.split('/')[0]
          this.ProjEditForm.startMonth = startTime.split('/')[1]
          this.ProjEditForm.endYear = endTime.split('/')[0]
          this.ProjEditForm.endMonth = endTime.split('/')[1]
        } else {
          this.$Message.error('查询数据失败！')
        }
      })
      .catch(() => {
        this.$Message.error('内部错误！')
      })
  },
  data () {
    const timeValidator = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入年份及月份！'))
      }
      if (this.ProjEditForm.startYear > this.ProjEditForm.endYear ||
      ((this.ProjEditForm.startYear === this.ProjEditForm.endYear) && (this.ProjEditForm.startMonth > this.ProjEditForm.endMonth))) {
        callback(new Error('请检查输入年月！'))
      } else {
        callback()
      }
    }

    return {
      years: [],
      months: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
      ProjEditForm: {
        intro: '',
        pos: '',
        prjname: '',
        duty: '',
        startYear: '',
        startMonth: '',
        endYear: '',
        endMonth: ''
      },
      sendProj: {
        id: 0,
        intro: '',
        pos: '',
        prjname: '',
        duty: '',
        time: ''
      },
      eduRules: {
        prjname: [
          {required: true, message: '请输入项目名称', trigger: 'blur'},
          { type: 'string', max: 50, message: '请不要输入超过50字', trigger: 'blur' }
        ],
        pos: [
          {required: true, message: '请输入项目职位', trigger: 'blur'},
          { type: 'string', max: 50, message: '请不要输入超过10字', trigger: 'blur' }
        ],
        intro: [
          {required: true, message: '请输入项目简介', trigger: 'blur'},
          { type: 'string', max: 300, message: '请不要输入超过300字', trigger: 'blur' }
        ],
        duty: [
          {required: true, message: '请输入项目职责', trigger: 'blur'},
          { type: 'string', max: 50, message: '请不要输入超过300字', trigger: 'blur' }
        ],
        startYear: [
          {validator: timeValidator, trigger: 'change'},
          {required: true, message: '请选择起始年份', trigger: 'blur'}
        ],
        startMonth: [
          {validator: timeValidator, trigger: 'change'},
          {required: true, message: '请选择起始月份', trigger: 'blur'}
        ],
        endYear: [
          {validator: timeValidator, trigger: 'change'},
          {required: true, message: '请选择结束年份', trigger: 'blur'}
        ],
        endMonth: [
          {validator: timeValidator, trigger: 'change'},
          {required: true, message: '请选择结束月份', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    handleCancel () {
      this.$router.push('/loginprojects')
    },
    handleSubmit (name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          let startTime = this.ProjEditForm.startYear + '/' + this.ProjEditForm.startMonth
          let endTime = this.ProjEditForm.endYear + '/' + this.ProjEditForm.endMonth
          this.sendProj.time = startTime + '-' + endTime
          this.sendProj.intro = this.ProjEditForm.intro
          this.sendProj.prjname = this.ProjEditForm.prjname
          this.sendProj.pos = this.ProjEditForm.pos
          this.sendProj.duty = this.ProjEditForm.duty
          console.log(this.sendProj)
          this.$axios({
            url: '/updateProj',
            method: 'post',
            data: this.sendProj
          }).then(res => {
            if (res.data.code === 200) {
              this.$Message.success('修改成功')
              this.$router.push('/loginprojects')
            } else {
              console.log(res.data.message)
              this.$Message.error('修改失败！')
            }
          }).catch(() => {
            this.$Message.error('内部错误！')
          })
        } else {
          this.$Message.error('请检查表单')
        }
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

.projects-card {
  margin: 0 auto;
  position: relative;
  width: 80%;
  max-width: 100%;
  border-radius: 5px;
  border: 1px solid rgba(34,36,38,.15);
  box-shadow: 0 1px 2px 0;
  background: #fff;
}

.projects-card > h1 {
  margin: 10px;
  text-align: center;
}

.project-edit {
  text-align: left;
  width: 50%;
  margin: 0 auto;
}

.project {
  line-height: 35px;
  flex-grow: 1;
  border: none;
  border-top: 1px solid;
}

.project > h2 {
  text-align: left;
  margin: 10px;
  font-size: 16px;
}

#time {
  text-align: right;
  margin: 10px;
  font-size: 14px;
}

.project > p {
  font-size: 16px;
  text-align: left;
  margin: 10px;
  color: #657180;
}

.button {
  text-align: center;
}

.colClass {
  padding-right: 10px;
}

</style>
