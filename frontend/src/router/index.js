import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/pages/Home'
import Education from '@/components/pages/Education'
import Projects from '@/components/pages/Projects'
import Login from '@/components/login/Login'
import LoginHome from '@/components/login/LoginHome'
import IntroEdit from '@/components/pages/IntroEdit'
import LoginMenu from '@/components/login/LoginMenu'
import LoginEdu from '@/components/login/LoginEdu'
import LoginProjects from '@/components/login/LoginProjects'
import EduEdit from '@/components/pages/EduEdit'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/education',
      name: 'education',
      component: Education
    },
    {
      path: '/projects',
      name: 'projects',
      component: Projects
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/loginhome',
      name: 'loginhome',
      component: LoginHome
    },
    {
      path: '/introedit',
      name: 'introedit',
      component: IntroEdit
    },
    {
      path: '/loginmenu',
      name: 'loginmenu',
      component: LoginMenu
    },
    {
      path: '/loginedu',
      name: 'loginedu',
      component: LoginEdu
    },
    {
      path: '/loginprojects',
      name: 'loginprojects',
      component: LoginProjects
    },
    {
      path: '/eduedit',
      name: 'eduedit',
      component: EduEdit
    }
  ]
})
