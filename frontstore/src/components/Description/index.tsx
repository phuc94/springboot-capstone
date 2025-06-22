import { Container } from "@mantine/core";
import { useEffect } from "react";

const Description = ({data}: {data: string}) => {

  // script for lazy load muga.me data
  useEffect(()=>{
    if (document.querySelector('frame#frame-loaded')) { return; }
    const divEl = document.querySelector('div.rll-youtube-player') as HTMLElement
    if (divEl) {
      const iframe = document.createElement("iframe")
      iframe.id = 'frame-loaded'
      iframe.width = '100%'
      iframe.height = '800'
      iframe.src = `${divEl.dataset.src}?${divEl.dataset.query}`
      iframe.allowFullscreen = true
      iframe.frameBorder = '0'
      iframe.allow = 'accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share'
      divEl.appendChild(iframe)
    }

    const pictureEls = document.querySelectorAll('picture.aligncenter.size-full') as NodeList
    pictureEls.forEach((el: any) => {
      el.childNodes[1].src = el.childNodes[1].dataset.lazySrc
      el.childNodes[1].style.width = '100%'
      el.childNodes[1].style.aspectRatio = 'auto'
      el.childNodes[1].style.height = 'auto'
      console.log(el.childNodes[1].dataset.lazySrc)
    })

  }, [data])

  return <Container>
      <div dangerouslySetInnerHTML={{__html: data}} />
    </Container>

}
export default Description;

