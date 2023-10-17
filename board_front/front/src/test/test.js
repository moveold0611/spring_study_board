// 뒤로 앞으로 캐시 테스트 js

window.addEventListener('pageshow', (event) => {
    // pageshow
    // 페이지가 처음 로드될 때
    // 페이지가 bfcache로부터 복원될 때
    if(event.persisted) {
    // event.persisted = bfcache에서 복원될 때 true값 return
    }
})

window.addEventListener('pagehide', (event) => {
    // pagehide
    // 페이지가 정상적으로 언로드(넘어갈) 때
    // 브라우저가 bfcache 하려고 시도할 때
    if (event.persisted) {
    }
})