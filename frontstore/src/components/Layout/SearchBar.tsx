import { TextInput } from "@mantine/core"
import { IconSearch } from "@tabler/icons-react";

const SearchBar = ({style}: {style: React.CSSProperties}) => {
  return (
    <TextInput
      placeholder="Tìm kiếm....." 
      rightSection={<SearchBtn/>}
      style={style}
    />
  )
}

const SearchBtn = () => ( <IconSearch />)

export default SearchBar;

