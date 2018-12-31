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
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/function/dashboard/index')
    }]
  },

  {
    path: 'ProjectPortrait',
    component: Layout,
    children: [
      {
        path: 'https://github.com/laoshubuluo/project_portrait',
        meta: { title: 'ProjectPortrait', icon: 'example' }
      }
    ]
  },

  {
    path: '/global',
    component: Layout,
    redirect: '/global/count',
    name: 'global',
    meta: { title: '项目全局', icon: 'example' },
    children: [
      {
        path: 'count',
        name: 'count',
        component: () => import('@/function/global/index'),
        meta: { title: '数量统计', icon: 'table' }
      },
      {
        path: 'hierarchy',
        name: 'hierarchy',
        component: () => import('@/function/global/index'),
        meta: { title: '层级关系统计', icon: 'tree' }
      }
    ]
  },

  {
    path: '/reference',
    component: Layout,
    redirect: '/reference/class',
    name: 'reference',
    meta: { title: '逻辑关系', icon: 'example' },
    children: [
      {
        path: 'class',
        name: 'class',
        component: () => import('@/function/reference/index'),
        meta: { title: '类引用关系', icon: 'table' }
      },
      {
        path: 'resource',
        name: 'resource',
        component: () => import('@/function/reference/index'),
        meta: { title: '资源引用关系', icon: 'tree' }
      }
    ]
  },

  {
    path: '/tag',
    component: Layout,
    redirect: '/tag/owner',
    name: 'tag',
    meta: { title: '标签', icon: 'example' },
    children: [
      {
        path: 'owner',
        name: 'owner',
        component: () => import('@/function/tag/index'),
        meta: { title: '归属者标签', icon: 'table' }
      },
      {
        path: 'function',
        name: 'function',
        component: () => import('@/function/tag/index'),
        meta: { title: '功能标签', icon: 'tree' }
      },
      {
        path: 'other',
        name: 'other',
        component: () => import('@/function/tag/index'),
        meta: { title: '其他标签', icon: 'table' }
      },
      {
        path: 'manage',
        name: 'manage',
        component: () => import('@/function/tag/index'),
        meta: { title: '标签管理', icon: 'table' }
      }
    ]
  },

  {
    path: '/better',
    component: Layout,
    redirect: '/better/resourceRepeat',
    name: 'better',
    meta: { title: '优化维度', icon: 'example' },
    children: [
      {
        path: 'resourceRepeat',
        name: 'resourceRepeat',
        component: () => import('@/function/better/index'),
        meta: { title: '资源重复', icon: 'table' }
      },
      {
        path: 'todo',
        name: 'todo',
        component: () => import('@/function/better/index'),
        meta: { title: 'TODO', icon: 'tree' }
      },
      {
        path: 'fileLine',
        name: 'fileLine',
        component: () => import('@/function/better/index'),
        meta: { title: '文件行数', icon: 'tree' }
      },
      {
        path: 'fileSize',
        name: 'fileSize',
        component: () => import('@/function/better/index'),
        meta: { title: '文件大小', icon: 'tree' }
      },
      {
        path: 'hardCode',
        name: 'hardCode',
        component: () => import('@/function/better/index'),
        meta: { title: '硬编码', icon: 'tree' }
      }
    ]
  },

  {
    path: '/customRule',
    component: Layout,
    redirect: '/customRule/singleRule',
    name: 'customRule',
    meta: { title: '规则无限可能', icon: 'example' },
    children: [
      {
        path: 'singleRule',
        name: 'singleRule',
        component: () => import('@/function/customrule/index'),
        meta: { title: '单一规则', icon: 'table' }
      },
      {
        path: 'crossRule',
        name: 'crossRule',
        component: () => import('@/function/customrule/index'),
        meta: { title: '交叉规则', icon: 'table' }
      },
      {
        path: 'allBean',
        name: 'allBean',
        component: () => import('@/function/customrule/index'),
        meta: { title: '-All bean', icon: 'tree' }
      },
      {
        path: 'allUICode',
        name: 'allUICode',
        component: () => import('@/function/customrule/index'),
        meta: { title: '-All UICode', icon: 'tree' }
      },
      {
        path: 'pageNoCode',
        name: 'pageNoCode',
        component: () => import('@/function/customrule/index'),
        meta: { title: '-PageNoCode', icon: 'tree' }
      },
      {
        path: 'routerNoParam',
        name: 'routerNoParam',
        component: () => import('@/function/better/index'),
        meta: { title: '-PageNoParam', icon: 'tree' }
      }
    ]
  },

  {
    path: '/console',
    component: Layout,
    redirect: '/console/command',
    name: 'tag',
    meta: { title: '控制台', icon: 'example' },
    children: [
      {
        path: 'command',
        name: 'command',
        component: () => import('@/function/console/index'),
        meta: { title: '命令', icon: 'table' }
      },
      {
        path: 'log',
        name: 'log',
        component: () => import('@/function/console/index'),
        meta: { title: '系统日志', icon: 'tree' }
      }
    ]
  },

  {
    path: '/about',
    component: Layout,
    children: [
      {
        path: 'about',
        name: 'about',
        component: () => import('@/function/about/index'),
        meta: { title: '关于', icon: 'form' }
      }
    ]
  },
  // {
  //   path: '/example',
  //   component: Layout,
  //   redirect: '/example/table',
  //   name: 'Example',
  //   meta: { title: 'Example', icon: 'example' },
  //   children: [
  //     {
  //       path: 'table',
  //       name: 'Table',
  //       component: () => import('@/views/table/index'),
  //       meta: { title: 'Table', icon: 'table' }
  //     },
  //     {
  //       path: 'tree',
  //       name: 'Tree',
  //       component: () => import('@/views/tree/index'),
  //       meta: { title: 'Tree', icon: 'tree' }
  //     }
  //   ]
  // },
  //
  // {
  //   path: '/form',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       name: 'Form',
  //       component: () => import('@/views/form/index'),
  //       meta: { title: 'Form', icon: 'form' }
  //     }
  //   ]
  // },
  //
  // {
  //   path: '/nested',
  //   component: Layout,
  //   redirect: '/nested/menu1',
  //   name: 'Nested',
  //   meta: {
  //     title: 'Nested',
  //     icon: 'nested'
  //   },
  //   children: [
  //     {
  //       path: 'menu1',
  //       component: () => import('@/views/nested/menu1/index'), // Parent router-view
  //       name: 'Menu1',
  //       meta: { title: 'Menu1' },
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: () => import('@/views/nested/menu1/menu1-1'),
  //           name: 'Menu1-1',
  //           meta: { title: 'Menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: () => import('@/views/nested/menu1/menu1-2'),
  //           name: 'Menu1-2',
  //           meta: { title: 'Menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  //               name: 'Menu1-2-1',
  //               meta: { title: 'Menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  //               name: 'Menu1-2-2',
  //               meta: { title: 'Menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: () => import('@/views/nested/menu1/menu1-3'),
  //           name: 'Menu1-3',
  //           meta: { title: 'Menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2',
  //       component: () => import('@/views/nested/menu2/index'),
  //       meta: { title: 'menu2' }
  //     }
  //   ]
  // },
  //
  // {
  //   path: 'external-link',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
  //       meta: { title: 'External Link', icon: 'link' }
  //     }
  //   ]
  // },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
