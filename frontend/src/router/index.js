import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/pages/Home'
import Education from '@/components/pages/Education'
import Projects from '@/components/pages/Projects'
import Login from '@/components/login/Login'

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
    }
  ]
})
