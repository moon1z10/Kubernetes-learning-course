import http from 'k6/http';
import { check } from 'k6';

export let options = {
    stages: [
        { duration: '1m', target: 500 },  // 1분 동안 500명의 사용자로 증가
        { duration: '3m', target: 1000 }, // 3분 동안 1000명의 사용자 유지
        { duration: '3m', target: 2000 }, // 3분 동안 2000명의 사용자 유지
        { duration: '1m', target: 0 },    // 1분 동안 0명으로 감소
    ]
};

export default function () {
    let res = http.get('http://localhost:8080/hello-world');
    check(res, {
        'is status 200': (r) => r.status === 200
    });
}
