import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/pages/Home'
import Eduction from '@/components/pages/Eduction'
import Projects from '@/components/pages/Projects'

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
      path: '/eduction',
      name: 'eduction',
      component: Eduction
    },
    {
      path: '/projects',
      name: 'projects',
      component: Projects
    }
  ]
})
