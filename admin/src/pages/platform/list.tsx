import {
  getDefaultSortOrder,
  useTable,
  List,
  ShowButton,
  EditButton
} from '@refinedev/antd'
import { Space, Table } from 'antd';

export const ListPlatform = () => {
  const {
    tableProps,
    sorters,
  } = useTable({
    sorters: {initial: [{field: "id", order: "asc"}]},
    syncWithLocation: true,
    pagination: {
      pageSize: 20
    }
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
        <Table.Column dataIndex="name" title="Name" />
        <Table.Column dataIndex="title" title="Title" />
        <Table.Column
          title="Parent"
          dataIndex={["parent", "title"]}
        />
        <Table.Column
          title="Actions"
          render={(_, record) => (
            <Space>
              <ShowButton hideText size="small" recordItemId={record.id} />
              <EditButton hideText size="small" recordItemId={record.id} />
            </Space>
          )}
        />
      </Table>
    </List>
  );
};
