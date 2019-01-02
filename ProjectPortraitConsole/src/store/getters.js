const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  // avatar: state => state.user.avatar,
  avatar: state => 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546433178130&di=db179a0c942836b78bbcb7c23bfc3eef&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F8cb1cb13495409235d4240119958d109b3de49b1.jpg',
  name: state => state.user.name,
  roles: state => state.user.roles
}
export default getters
