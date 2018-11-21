const name = 'zhoulinshun';
const password = 'r411LuV84H12mn22';

const ssoUrl = 'https://sso.wecash.net/login';
let wifiHost = '172.16.0.92';
const wifiHostDefault = '172.16.0.92';
const wifiApiUrl = `http://${wifiHost}/api/portal/login`;
const wifiIndexUrl = `http://${wifiHost}/portal/login`;
const punchUrl = 'https://hr.wecash.net/punch';
const wifiTestUrl = 'http://163.cn/'


const corpid = 'ww3fdb8abe8a62e41f'
const testSecret = '9gLBm9vaMMtxbFe-rFWhPMyR7_F7RxLcZzrh4yEUTJo'
const wxSendUrl = 'https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s'
const wxGetTokenUrl = `https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=${corpid}&corpsecret=%s`


const dateApi = 'http://www.easybots.cn/api/holiday.php?d=%s'

module.exports = {
    name,
    password,
    ssoUrl,
    wifiApiUrl,
    punchUrl,
    wifiTestUrl,
    wifiHost,
    wifiIndexUrl,
    wifiHostDefault,
    wxSendUrl,
    testSecret,
    corpid,
    wxGetTokenUrl,
    dateApi
}