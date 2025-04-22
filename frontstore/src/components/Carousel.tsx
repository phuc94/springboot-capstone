import { Carousel } from "@mantine/carousel"
import { Image } from "@mantine/core";
import Autoplay from "embla-carousel-autoplay";
import { useRef } from "react";

const AppCarousel = () => {
  const autoplay = useRef(Autoplay({ delay: 5000 }))

  return (
    <Carousel
      withIndicators
      height={450}
      plugins={[autoplay.current]}
      onMouseEnter={autoplay.current.stop}
      onMouseLeave={autoplay.current.reset}
      loop
    >
      <Carousel.Slide>
        <Image height="450" src="https://muaga.me/wp-content/uploads/2022/03/ubisoft-plus_hero-1.jpeg.webp" />
      </Carousel.Slide>
      <Carousel.Slide>
        <Image height="450" src="https://muaga.me/wp-content/uploads/2022/03/kiem-tien-cung-muagame-pc2.png.webp" />
      </Carousel.Slide>
    </Carousel>
  )
}

export default AppCarousel;

