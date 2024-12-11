import Vue from 'vue'
import Router from 'vue-router'


// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRoutes = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  // 首页
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: '教培管理系统',
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '教培管理系统', icon: 'index' }
    }]
  }
]
export const asyncRoutes = [
  {
    path: '/course',
    component: Layout,
    redirect: '/course/table',
    alwaysShow: true,
    name: '课程信息管理',
    meta: { title: '课程信息管理', icon: 'pj'},
    children: [
      {
        path: 'table',
        name: '课程信息',
        component: () => import('@/views/course/index.vue'),
        meta: { title: '课程信息', icon: 'table' }
      },
      {
        path: 'courseCategory',
        name: '课程分类信息',
        component: () => import('@/views/courseCategory/index.vue'),
        meta: { title: '课程分类信息', icon: 'table' }
      },
      {
        path: 'courseAppointment',
        name: '课程预约记录',
        component: () => import('@/views/courseAppointment/index.vue'),
        meta: { title: '课程预约记录', icon: 'table' }
      }
    ]
  },
  {
    path: '/student',
    component: Layout,
    redirect: '/student/table',
    alwaysShow: true,
    name: '学生信息管理',
    meta: { title: '学生信息管理', icon: 'pj'},
    children: [
      {
        path: 'table',
        name: '学生信息',
        component: () => import('@/views/student/index.vue'),
        meta: { title: '学生信息', icon: 'table' }
      }
    ]
  },
  {
    path: '/teacher',
    component: Layout,
    redirect: '/teacher/table',
    alwaysShow: true,
    name: '讲师信息管理',
    meta: { title: '讲师信息管理', icon: 'pj'},
    children: [
      {
        path: 'table',
        name: '讲师信息',
        component: () => import('@/views/teacher/index.vue'),
        meta: { title: '讲师信息', icon: 'table' }
      }
    ]
  },
  {
    path: '/banner',
    component: Layout,
    redirect: '/banner/table',
    alwaysShow: true,
    name: '轮播图管理',
    meta: { title: '轮播图管理', icon: 'pj'},
    children: [
      {
        path: 'table',
        name: '轮播图信息',
        component: () => import('@/views/banner/index.vue'),
        meta: { title: '轮播图信息', icon: 'table' }
      }
    ]
  },
  {
    path: '/notice',
    component: Layout,
    redirect: '/notice/table',
    alwaysShow: true,
    name: '通知管理',
    meta: { title: '通知管理', icon: 'pj'},
    children: [
      {
        path: 'table',
        name: '通知信息',
        component: () => import('@/views/notice/index.vue'),
        meta: { title: '通知信息', icon: 'table' }
      }
    ]
  },
  {
    path: '/class',
    component: Layout,
    redirect: '/class/table',
    alwaysShow: true,
    name: '班级信息管理',
    meta: { title: '班级信息管理', icon: 'pj', roles: ['admin']},
    children: [
      {
        path: 'table',
        name: '班级信息',
        component: () => import('@/views/class/index.vue'),
        meta: { title: '班级信息', icon: 'table' }
      }
    ]
  },
  {
    path: '/score',
    component: Layout,
    redirect: '/score/table',
    alwaysShow: true,
    name: '成绩信息管理',
    meta: { title: '成绩信息管理', icon: 'pj'},
    children: [
      {
        path: 'table',
        name: '成绩信息',
        component: () => import('@/views/score/index.vue'),
        meta: { title: '成绩信息', icon: 'table' }
      }
    ]
  },
  // 系统管理
  {
    path: '/system',
    redirect: '/system/user', // 默认访问地址
    component: Layout,
    meta: { title: '系统管理', icon: 'ck'},
    children: [
      {
        path: 'updatePassword',
        name: '密码修改',
        component: () => import('@/views/system/updatePassword'),
        meta: { title: '密码修改', icon: 'dr'  }
      },
      {
        path: 'info',
        name: 'info',
        component: () => import('@/views/system/info'),
        meta: { title: '个人中心', icon: 'dr'}
      }
    ]
  },
]
const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
