import { useMany, getDefaultFilter } from "@refinedev/core";
import {
  EditButton,
  getDefaultSortOrder,
  ShowButton,
  useTable,
  FilterDropdown,
  useSelect,
  List
} from '@refinedev/antd'
import { Table, Space, Input, Select } from 'antd';


export const ListProducts = () => {
  const {
    tableProps,
    sorters,
    filters
  } = useTable({
    sorters: {initial: [{field: "id", order: "asc"}]},
    syncWithLocation: true,
    pagination: { current: 1, pageSize: 10 },
    filters: {
      initial: [{field: "category.id", operator: "eq", value: 2}]
    }
  })

  const {data: categories, isLoading} = useMany({
    resource: "categories",
    ids: tableProps?.dataSource?.map((product: any) => product.category?.id) ?? []
  });

  const { selectProps } = useSelect({
    resource: "categories",
  })

  return (
    <List>
      <Table {...tableProps} rowKey="id">
        <Table.Column
          dataIndex="id"
          title="ID"
          sorter
          defaultSortOrder={getDefaultSortOrder("id", sorters)}
        />
        <Table.Column
          dataIndex="name"
          title="Name"
          sorter
          defaultSortOrder={getDefaultSortOrder("name", sorters)}
          filterDropdown={props => (
            <FilterDropdown {...props}>
              <Input />
            </FilterDropdown>
          )}
         />
        <Table.Column
          dataIndex={["category", "id"]}
          title="Category"
          render={(value) => {
            if (isLoading) {
              return "Loading...";
            }

            return categories?.data?.find((category) => category.id == value)
              ?.title;
          }}
          filterDropdown={props => (
            <FilterDropdown
              {...props}
              mapValue={(selectedKey) => Number(selectedKey)}
            >
              <Select style={{minWidth: 200}} {...selectProps} />
            </FilterDropdown>
          )}
          defaultFilteredValue={getDefaultFilter("category.id", filters, "eq")}
        />
        <Table.Column dataIndex="material" title="Material" />
        <Table.Column dataIndex="price" title="Price" />
        <Table.Column
          title="Actions"
          render={(_, record) => (
            <Space>
              {/* We'll use the `EditButton` and `ShowButton` to manage navigation easily */}
              <ShowButton hideText size="small" recordItemId={record.id} />
              <EditButton hideText size="small" recordItemId={record.id} />
            </Space>
          )}
        />
      </Table>
    </List>
  );
};
