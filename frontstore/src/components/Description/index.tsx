import { Container } from "@mantine/core";
import { useDescription } from "../../hooks/useDescription";
import { useEffect } from "react";

const Description = () => {
  const {isSuccess, data} = useDescription()

  useEffect(()=>{
    if (document.querySelector('frame#frame-loaded')) {
      console.log('loaded')
      return
    }
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

  if (isSuccess) {
    return <Container>
      <div dangerouslySetInnerHTML={{__html: data?.data[0].description}} />
    </Container>
  }

  return null
}
export default Description;


