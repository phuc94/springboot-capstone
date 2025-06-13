import { Container } from "@mantine/core";
import { useEffect } from "react";

const Description = ({data}: {data: string}) => {

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
  }, [data])

  return <Container>
      <div dangerouslySetInnerHTML={{__html: data}} />
    </Container>

}
export default Description;


