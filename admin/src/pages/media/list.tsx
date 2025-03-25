import {
  getDefaultSortOrder,
  useTable,
  List,
  ShowButton,
  EditButton
} from '@refinedev/antd'
import { Space, Switch, Table } from 'antd';


export const ListMedia = () => {
  const {
    tableProps,
    sorters,
  } = useTable({
    sorters: {initial: [{field: "id", order: "asc"}]},
    syncWithLocation: true,
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
        <Table.Column dataIndex="url" title="Url" />
        <Table.Column dataIndex="media_type" title="Media Type" />
        <Table.Column dataIndex="is_primary" title="Is Primary" />
        <Table.Column title="Is Primary"
          render={(_, record) => (
            <Switch value={record.primary} disabled/>
          )}
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
