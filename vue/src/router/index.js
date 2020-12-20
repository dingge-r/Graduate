import Vue from 'vue'
import Router from 'vue-router'
// in development env not use Lazy Loading,because Lazy Loading too many pages will cause webpack hot update too slow.so only in production use Lazy Loading
/* layout */
import Layout from '../views/layout/Layout'

const _import = require('./_import_' + process.env.NODE_ENV)
Vue.use(Router)
export const constantRouterMap = [
  {path: '/login', component: _import('login/index'), hidden: true},
  {path: '/404', component: _import('404'), hidden: true},
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '首页',
    hidden: true,
    children: [{
      path: 'dashboard', component: _import('dashboard/index')
    }]
  }
]
export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})
export const asyncRouterMap = [
  // {
  //   path: '/article',
  //   component: Layout,
  //   redirect: '/system/article',
  //   name: '功能模块',
  //   meta: { title: '功能模块', icon: 'tree' },
  //   children: [
  //     {
  //       path: 'article',
  //       name: '文章管理',
  //       component: _import('article/article'),
  //       meta: { title: '文章管理', icon: 'example' },
  //       menu: 'article'
  //     },
  //   ]
  // },
  {
    path: '/system',
    component: Layout,
    redirect: '/system/student',
    name: '功能模块',
    meta: {title: '功能模块', icon: 'tree'},
    children: [
      {
        path: 'article',
        name: '学生信息管理',
        component: _import('student/student'),
        meta: { title: '学生信息管理', icon: 'example'},
        menu: 'student'
      },
    ]
  },
  {
    path: '/recruit',
    component: Layout,
    redirect: '/recruit/recruit',
    name: '招聘信息管理',
    meta: { title: '功能模块', icon: 'tree' },
    children: [
      {
        path: 'article',
        name: '招聘信息管理',
        component: _import('recruit/recruit'),
        meta: { title: '招聘信息管理', icon: 'example' },
        menu: 'recruit'
      },
    ]
  },
  {
    path: '/notice',
    component: Layout,
    redirect: '/notice/notice',
    name: '公告管理',
    meta: { title: '功能模块', icon: 'tree' },
    children: [
      {
        path: 'article',
        name: '公告管理',
        component: _import('notice/notice'),
        meta: { title: '公告管理', icon: 'example' },
        menu: '公告管理'
      },
    ]
  },
  {
    path: '/resume',
    component: Layout,
    redirect: '/resume/resume',
    name: '简历管理',
    meta: { title: '功能模块', icon: 'tree' },
    children: [
      {
        path: 'article',
        name: '简历管理',
        component: _import('resume/resume'),
        meta: { title: '简历管理', icon: 'example' },
        menu: 'resume'
      },
    ]
  },
 
  {
    path: '/user',
    component: Layout,
    redirect: '/user/',
    name: '',
    meta: {title: '用户权限', icon: 'table'},
    children: [
      {
        path: '', name: '用户列表', component: _import('user/user'), meta: {title: '用户列表', icon: 'user'}, menu: 'user'
      },
      {
        path: 'role',
        name: '权限管理',
        component: _import('user/role'),
        meta: {title: '权限管理', icon: 'password'},
        menu: 'role'
      },
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]
