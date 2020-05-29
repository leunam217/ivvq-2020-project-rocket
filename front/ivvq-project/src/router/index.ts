import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '@/views/Login.vue'
import Product from '@/components/Product.vue'
import Payment from '@/views/Payment.vue'
import Register from '@/views/Register.vue'
import { MainModule } from '@/components/mainStoreModule'

Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    beforeEnter(_to, _from, next) {
      console.log(MainModule.getState)
      if (MainModule.getState.jwtResponse?.token === undefined)
        next({ name: 'Login' })
      else next()
    }
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    beforeEnter(_to, _from, next) {
      if (MainModule.getState.jwtResponse?.token !== undefined)
        next({ name: 'Home' })
      else next()
    }
  },
  {
    path: '/test',
    name: 'Test',
    component: Product
  },
  {
    path: '/pay',
    name: 'Pay',
    component: Payment
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
